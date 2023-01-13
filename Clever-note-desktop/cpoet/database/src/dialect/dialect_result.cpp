// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/2

#include "inc/dialect/dialect_result.h"

DialectResult::DialectResult() : _params(nullptr), _failed(false) {
}

DialectResult::~DialectResult() {
    delete this->_params;
}

const QString &DialectResult::getSql() const {
    return this->_sql;
}

void DialectResult::setSql(const QString &sql) {
    this->_sql = sql;
}

const QVariantList *DialectResult::getParams() const {
    return this->_params;
}

void DialectResult::setParams(QVariantList *params) {
    if (params != this->_params) {
        delete this->_params;
    }
    this->_params = params;
}

bool DialectResult::isFailed() const {
    return _failed;
}

void DialectResult::setFailed(bool failed) {
    _failed = failed;
}

const QString &DialectResult::getMessage() const {
    return _message;
}

void DialectResult::setMessage(const QString &message) {
    _message = message;
}

DialectResult *DialectResult::appendSql(const char *sql) {
    this->_sql.append(sql);
    return this;
}

DialectResult *DialectResult::appendSql(const QString &sql) {
    this->_sql.append(sql);
    return this;
}

DialectResult *DialectResult::addParam(const QVariant &param) {
    if (this->_params == nullptr) {
        this->_params = new QVariantList();
    }
    this->_params->append(param);
    return this;
}

DialectResult *DialectResult::addParams(const QVariantList &params) {
    if (this->_params == nullptr) {
        this->_params = new QVariantList();
    }
    this->_params->append(params);
    return this;
}
