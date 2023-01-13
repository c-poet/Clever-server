// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/31

#ifndef C_POET_WHERE_WRAPPER_H
#define C_POET_WHERE_WRAPPER_H

#include "sql_wrapper.h"
#include "sql_condition.h"


/// @brief 条件Wrapper
template<class W>
class WhereWrapper : virtual public SqlWrapper {
public:
    /// @brief 表达式链式
    class ConditionChain {
    private:
        friend class WhereWrapper<W>;

        // 下级节点
        ConditionChain *_next;
        // 本级表达式
        SqlCondition *_condition;
    public:
        ConditionChain() : _condition(nullptr) {}

        virtual ~ConditionChain() {
            delete this->_condition;
        }

        /// @brief 获取下个节点
        /// @return 下个节点
        ConditionChain *getNext() const {
            return _next;
        }

        /// @brief 获取当前表达式
        /// @return 当前表达式
        SqlCondition *getCondition() const {
            return _condition;
        };
    };

private:
    ConditionChain *_start;
    ConditionChain *_end;

    ConditionChain *newConditionChain(SqlCondition *sqlCondition) {
        auto *chain = new ConditionChain();
        chain->_condition = sqlCondition;
        chain->_next = nullptr;
        return chain;
    }

public:
    WhereWrapper() : _start(nullptr), _end(nullptr) {}

    ~WhereWrapper() override {
        if (this->_start != nullptr) {
            auto *curChain = this->_start;
            ConditionChain *swapChain;
            while (curChain != nullptr) {
                swapChain = curChain->_next;
                delete curChain;
                curChain = swapChain;
            }
        }
    }

    /// @brief 为Null值
    /// @param val 值
    /// @return this 实例
    W *isNull(const QString &val) {
        auto *condition = (new UnaryCondition(SqlConditionTypes::IS_NULL))
                ->setValue(val)
                ->setCol(true);
        return this->addCondition(condition);
    }

    /// @brief 判断不为Null值
    /// @param val 值
    /// @return this 实例
    W *notNull(const QString &val) {
        auto *condition = (new UnaryCondition(SqlConditionTypes::NOT_NULL))
                ->setValue(val)
                ->setCol(true);
        return this->addCondition(condition);
    }

    /// @brief 判断是否相等
    /// @param leftVal 左值
    /// @param rightVal 右值
    /// @return this 实例
    W *eq(const QVariant &leftVal, const QVariant &rightVal) {
        return this->eq(leftVal, rightVal, false);
    }

    /// @brief 判断是否相等
    /// @param leftVal 左值
    /// @param rightVal 右值
    /// @param rightCol 右值是否是列
    /// @return this 实例
    W *eq(const QVariant &leftVal, const QVariant &rightVal, bool rightCol) {
        return this->eq(leftVal, rightVal, true, rightCol);
    }

    /// @brief 判断是否相关
    /// @param leftVal 左值
    /// @param rightVal 右值
    /// @param leftCol 左值是否是列
    /// @param rightCol 右值是否是列
    /// @return this 实例
    W *eq(const QVariant &leftVal, const QVariant &rightVal, bool leftCol, bool rightCol) {
        auto *condition = (new BinaryCondition(SqlConditionTypes::EQ))
                ->setLeftValue(leftVal)
                ->setRightValue(rightVal)
                ->setLeftCol(leftCol)
                ->setRightCol(rightCol);
        return this->addCondition(condition);
    }

    /// @brief 添加And逻辑符
    /// @return this 实例
    W *logicAnd() {
        return addCondition(new SqlLogicCondition(SqlConditionLogic::AND));
    }

    /// @brief 添加Or逻辑符
    /// @return this 实例
    W *logicOr() {
        return addCondition(new SqlLogicCondition(SqlConditionLogic::OR));
    }

    /// @brief 添加左界符
    /// @return this 实例
    W *regionLeft() {
        return addCondition(new SqlRegionCondition(SqlConditionRegion::LEFT));
    }

    /// @brief 添加右界符
    /// @return this 实例
    W *regionRight() {
        return addCondition(new SqlRegionCondition(SqlConditionRegion::RIGHT));
    }

    /// @brief 添加表达式
    /// @param sqlCondition 表达式实例
    /// @return this 实例
    W *addCondition(SqlCondition *sqlCondition) {
        auto *newChain = this->newConditionChain(sqlCondition);
        if (this->_end == nullptr) {
            this->_start = newChain;
            this->_end = newChain;
        } else {
            SqlConditionHeads conditionHead = sqlCondition->head();
            SqlCondition *lastCondition = this->_end->getCondition();
            SqlConditionHeads lastConditionHead = lastCondition->head();
            if (conditionHead != SqlConditionHeads::LOGIC) {
                if (lastConditionHead != SqlConditionHeads::LOGIC) {
                    if (lastConditionHead != SqlConditionHeads::REGION
                        || dynamic_cast<SqlRegionCondition *>(lastCondition)->getRegion() != SqlConditionRegion::LEFT) {
                        if (conditionHead != SqlConditionHeads::REGION ||
                            dynamic_cast<SqlRegionCondition *>(sqlCondition)->getRegion() !=
                            SqlConditionRegion::RIGHT) {
                            // 默认添加and连接符
                            this->logicAnd();
                        }
                    }
                }
            }
            this->_end->_next = newChain;
            this->_end = newChain;
        }
        return dynamic_cast<W *>(this);
    }

    /// @brief 结果消费
    /// @param consumer 消费者
    void consume(const std::function<void(ConditionChain *, ConditionChain *)> &consumer) {
        consumer(this->_start, this->_end);
    }
};

#endif
