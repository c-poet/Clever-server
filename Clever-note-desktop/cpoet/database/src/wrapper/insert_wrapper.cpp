// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/1

#include <QVariant>
#include <inc/wrapper/insert_wrapper.h>

InsertWrapper::InsertWrapper() : _columns(nullptr), _values(nullptr) {};

InsertWrapper::InsertWrapper(const QString &table) : _table(table), _columns(nullptr), _values(nullptr) {
}

InsertWrapper::~InsertWrapper() {
    delete this->_columns;
    delete this->_values;
}

const QString &InsertWrapper::getTable() const {
    return _table;
}

InsertWrapper *InsertWrapper::setTable(const QString &table) {
    _table = table;
    return this;
}

InsertWrapper *InsertWrapper::setNull(const QString &column) {
    return this->set(column, QVariant());
}

InsertWrapper *InsertWrapper::set(const QString &column, const QVariant &value) {
    if (this->_columns == nullptr) {
        this->_columns = new QList<QString>();
        this->_values = new QVariantList();
    }
    this->_columns->append(column);
    this->_values->append(value);
    return this;
}

void InsertWrapper::consume(const std::function<void(QList<QString> *, QVariantList *)> &consumer) {
    consumer(this->_columns, this->_values);
}
