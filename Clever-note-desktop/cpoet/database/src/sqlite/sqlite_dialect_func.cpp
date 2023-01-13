// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/2

#include "cpoet/core/inc/log.h"
#include "inc/sqlite/sqlite_keywords.h"
#include "inc/sqlite/sqlite_dialect_func.h"

inline void handleFailed(DialectResult *result, const QList<QString> &message = QList<QString>()) {
    result->setFailed(true);
    if (!message.isEmpty()) {
        result->setMessage(message.join(""));
    }
}

inline DialectResult *handleAppendField(DialectResult *result, const QVariant &field, bool isCol) {
    if (isCol) {
        result->appendSql(field.toString());
    } else {
        result->appendSql(SYMBOL_QM)->addParam(field);
    }
    return result;
}

inline DialectResult *handleAppendKeyword(DialectResult *result, const QList<const char *> &keywords) {
    for (const auto &keyword: keywords) {
        result->appendSql(SYMBOL_SPACE)->appendSql(keyword);
    }
    return result;
}

void handleSelect(SelectWrapper *wrapper, DialectResult *result, DialectContext *context) {
    wrapper->consume([&wrapper, &result](SelectWrapper::ColumnChain *start, SelectWrapper::ColumnChain *end) {
        result->appendSql(KEYWORD_SELECT);
        if (start == nullptr) {
            result->appendSql(SYMBOL_SPACE)
                    ->appendSql(SYMBOL_ASTERISK);
        } else {
            SelectWrapper::ColumnChain *nextColumn;
            while (start != nullptr) {
                nextColumn = start->getNext();
                result->appendSql(SYMBOL_SPACE)
                        ->appendSql(start->getName());
                if (!start->getAlias().isEmpty()) {
                    result->appendSql(SYMBOL_SPACE)
                            ->appendSql(KEYWORD_AS)
                            ->appendSql(SYMBOL_SPACE)
                            ->appendSql(start->getAlias());
                }
                if (nextColumn != nullptr) {
                    result->appendSql(SYMBOL_COMMA);
                }
                start = nextColumn;
            }
        }
        result->appendSql(SYMBOL_SPACE)
                ->appendSql(KEYWORD_FROM)
                ->appendSql(SYMBOL_SPACE)
                ->appendSql(wrapper->getTable());
        if (!wrapper->getAlias().isEmpty()) {
            result->appendSql(SYMBOL_SPACE)
                    ->appendSql(KEYWORD_AS)
                    ->appendSql(SYMBOL_SPACE)
                    ->appendSql(wrapper->getAlias());
        }
    });
    handleWhere<SelectWrapper>(wrapper, result, context);
    handleOrder<SelectWrapper>(wrapper, result, context);
}

void handleInsert(InsertWrapper *wrapper, DialectResult *result, DialectContext *context) {
    wrapper->consume([&wrapper, &result](QList<QString> *columns, QVariantList *values) {
        if (columns == nullptr) {
            handleFailed(result, {"未指定列值"});
        } else {
            result->appendSql(KEYWORD_INSERT)
                    ->appendSql(SYMBOL_SPACE)
                    ->appendSql(KEYWORD_INTO)
                    ->appendSql(SYMBOL_SPACE)
                    ->appendSql(wrapper->getTable())
                    ->appendSql(SYMBOL_L_BRACKET)
                    ->appendSql(columns->join(SYMBOL_COMMA))
                    ->appendSql(SYMBOL_R_BRACKET)
                    ->appendSql(SYMBOL_SPACE)
                    ->appendSql(KEYWORD_VALUES)
                    ->appendSql(SYMBOL_L_BRACKET)
                    ->appendSql(SYMBOL_QM);
            int len = columns->size();
            for (int i = 1; i < len; ++i) {
                result->appendSql(SYMBOL_COMMA)
                        ->appendSql(SYMBOL_QM);
            }
            result->appendSql(SYMBOL_R_BRACKET)
                    ->addParams(*values);
        }
    });
}

void handleUpdate(UpdateWrapper *wrapper, DialectResult *result, DialectContext *context) {
    wrapper->consume([&wrapper, &result](UpdateWrapper::UpdateMapperChain *start,
                                         UpdateWrapper::UpdateMapperChain *end) {
        if (start == nullptr) {
            handleFailed(result, {"未配置需要更新的列和值"});
        }
        result->appendSql(KEYWORD_UPDATE)
                ->appendSql(SYMBOL_SPACE)
                ->appendSql(wrapper->getTable())
                ->appendSql(SYMBOL_SPACE)
                ->appendSql(KEYWORD_SET)
                ->appendSql(SYMBOL_SPACE);
        do {
            result->appendSql(start->getColumn())
                    ->appendSql(SYMBOL_SPACE)
                    ->appendSql(SYMBOL_EQ)
                    ->appendSql(SYMBOL_SPACE);
            if (start->isCol()) {
                result->appendSql(start->getVal().toString());
            } else {
                result->appendSql(SYMBOL_QM)
                        ->addParam(start->getVal());
            }
            start = start->getNext();
            if (start != nullptr) {
                result->appendSql(SYMBOL_COMMA);
            }
        } while (start != nullptr);
    });
    handleWhere<UpdateWrapper>(wrapper, result, context);
}

void handleDelete(DeleteWrapper *wrapper, DialectResult *result, DialectContext *context) {
    result->appendSql(KEYWORD_DELETE)
            ->appendSql(SYMBOL_SPACE)
            ->appendSql(KEYWORD_FROM)
            ->appendSql(SYMBOL_SPACE)
            ->appendSql(wrapper->getTable());
    handleWhere<DeleteWrapper>(wrapper, result, context);
}

void handleUnaryCondition(UnaryCondition *condition, DialectResult *result, DialectContext *context) {
    switch (condition->getType()) {
        case SqlConditionTypes::IS_NULL:
            handleAppendField(result, condition->getValue(), condition->isCol());
            handleAppendKeyword(result, {KEYWORD_IS, KEYWORD_NULL});
            break;
        case SqlConditionTypes::NOT_NULL:
            handleAppendField(result, condition->getValue(), condition->isCol());
            handleAppendKeyword(result, {KEYWORD_IS, KEYWORD_NOT, KEYWORD_NULL});
            break;
        default:
            handleFailed(result, {"不支持的一目表达式类型：", typeid(condition->getType()).name()});
    }
}

void handleBinaryCondition(BinaryCondition *condition, DialectResult *result, DialectContext *context) {
    switch (condition->getType()) {
        case SqlConditionTypes::EQ:
            handleAppendField(result, condition->getLeftValue(), condition->isLeftCol())
                    ->appendSql(SYMBOL_SPACE)
                    ->appendSql(SYMBOL_EQ)
                    ->appendSql(SYMBOL_SPACE);
            handleAppendField(result, condition->getRightValue(), condition->isRightCol());
            break;
        default:
            handleFailed(result, {"不支持的二目表达式类型:", typeid(condition->getType()).name()});
    }
}

void handleTernaryCondition(TernaryCondition *condition, DialectResult *result, DialectContext *context) {

}

void handleWrapperCondition(SqlWrapperCondition *condition, DialectResult *result, DialectContext *context) {
    auto *wrapper = condition->getWrapper();
    if (wrapper->type() == SqlWrapperTypes::QUERY) {
        handleSelect(dynamic_cast<SelectWrapper *>(wrapper), result, context);
    } else {
        handleFailed(result, {"目前WrapperCondition仅支持Query类型:", typeid(wrapper->type()).name()});
    }
}

template<class W>
void handleWhere(WhereWrapper<W> *wrapper, DialectResult *result, DialectContext *context) {
    wrapper->consume([&result, &context](typename WhereWrapper<W>::ConditionChain *start,
                                         typename WhereWrapper<W>::ConditionChain *end) {
        if (start != nullptr) {
            result->appendSql(SYMBOL_SPACE)->appendSql(KEYWORD_WHERE);
            do {
                auto *condition = start->getCondition();
                result->appendSql(SYMBOL_SPACE);
                switch (condition->head()) {
                    case SqlConditionHeads::UNARY:
                        handleUnaryCondition(dynamic_cast<UnaryCondition *>(condition), result, context);
                        break;
                    case SqlConditionHeads::BINARY:
                        handleBinaryCondition(dynamic_cast<BinaryCondition *>(condition), result, context);
                        break;
                    case SqlConditionHeads::TERNARY:
                        handleTernaryCondition(dynamic_cast<TernaryCondition *>(condition), result, context);
                        break;
                    case SqlConditionHeads::WRAPPER:
                        handleWrapperCondition(dynamic_cast<SqlWrapperCondition *>(condition), result, context);
                        break;
                    case SqlConditionHeads::LOGIC:
                        result->appendSql(dynamic_cast<SqlLogicCondition *>(condition)->getLogic()
                                          == SqlConditionLogic::OR ? KEYWORD_OR : KEYWORD_AND);
                        break;
                    case SqlConditionHeads::REGION:
                        result->appendSql(dynamic_cast<SqlRegionCondition *>(condition)->getRegion()
                                          == SqlConditionRegion::RIGHT ? SYMBOL_R_BRACKET : SYMBOL_L_BRACKET);
                        break;
                    default:
                        handleFailed(result, {"不支持的Where表达式头:", typeid(condition->head()).name()});
                }
                start = start->getNext();
            } while (start != nullptr && !result->isFailed());
        }
    });
}

#define FIX_ORDER_TYPE(orderType) ((orderType) == OrderTypes::DESC ? KEYWORD_DESC : KEYWORD_ASC)

template<class W>
void handleOrder(OrderWrapper<W> *wrapper, DialectResult *result, DialectContext *context) {
    wrapper->consume([&result](typename OrderWrapper<W>::OrderMapperChain *start,
                               typename OrderWrapper<W>::OrderMapperChain *end) {
        if (start != nullptr) {
            handleAppendKeyword(result, {KEYWORD_ORDER, KEYWORD_BY})
                    ->appendSql(SYMBOL_SPACE)
                    ->appendSql(start->getColumn())
                    ->appendSql(SYMBOL_SPACE)
                    ->appendSql(FIX_ORDER_TYPE(start->getOrderType()));
            start = start->getNext();
            while (start != nullptr) {
                result->appendSql(SYMBOL_COMMA)
                        ->appendSql(SYMBOL_SPACE)
                        ->appendSql(start->getColumn())
                        ->appendSql(SYMBOL_SPACE)
                        ->appendSql(FIX_ORDER_TYPE(start->getOrderType()));
                start = start->getNext();
            }
        }
    });
}
