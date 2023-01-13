// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/1

#include "inc/wrapper/delete_wrapper.h"

DeleteWrapper::DeleteWrapper() = default;

DeleteWrapper::DeleteWrapper(const QString &table) : _table(table) {
}

const QString &DeleteWrapper::getTable() const {
    return _table;
}

DeleteWrapper *DeleteWrapper::setTable(const QString &table) {
    _table = table;
    return this;
}
