// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/3

#include "cpoet/core/inc/log.h"
#include "inc/dialect/dialect_factory.h"
#include "inc/wrapper/wrapper_executor.h"
#include "inc/wrapper/select_wrapper.h"

int WrapperExecutor::execute(SqlWrapper *wrapper) {
    int result = 0;
    this->execute(wrapper, [this, &result](DialectResult *dialectResult) {
        auto *params = dialectResult->getParams();
        if (params == nullptr) {
            result = this->SqlExecutor::execute(dialectResult->getSql());
        } else {
            result = this->SqlExecutor::execute(dialectResult->getSql(), *params);
        }
    });
    return result;
}

void WrapperExecutor::execute(SqlWrapper *wrapper, const std::function<void(DialectResult *)> &callback) {
    auto *dialectResult = DialectFactory::dialectSilence(this->_db.driverName(), wrapper);
    if (dialectResult == nullptr) {
        qWarning() << "wrapper执行回调失败，翻译结果为空";
    } else if (dialectResult->isFailed()) {
        qWarning() << "wrapper执行回调失败，原因：" << dialectResult->getMessage();
        delete dialectResult;
    } else {
        callback(dialectResult);
        delete dialectResult;
    }
}

QVariantMap *WrapperExecutor::findOne(SelectWrapper *wrapper) {
    QVariantMap *result = nullptr;
    this->execute(wrapper, [this, &result](DialectResult *dialectResult) {
        auto *params = dialectResult->getParams();
        if (params == nullptr) {
            result = this->SqlExecutor::findOne(dialectResult->getSql());
        } else {
            result = this->SqlExecutor::findOne(dialectResult->getSql(), *params);
        }
    });
    return result;
}

QList<QVariantMap> *WrapperExecutor::findList(SelectWrapper *wrapper) {
    QList<QVariantMap> *result = nullptr;
    this->execute(wrapper, [this, &result](DialectResult *dialectResult) {
        auto *params = dialectResult->getParams();
        if (params == nullptr) {
            result = this->SqlExecutor::findList(dialectResult->getSql());
        } else {
            result = this->SqlExecutor::findList(dialectResult->getSql(), *params);
        }
    });
    return result;
}

