// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/1

#include "inc/wrapper/sql_wrappers.h"

UpdateWrapper *SqlWrappers::update() {
    return new UpdateWrapper();
}

UpdateWrapper *SqlWrappers::update(const QString &table) {
    return new UpdateWrapper(table);
}

DeleteWrapper *SqlWrappers::deleted() {
    return new DeleteWrapper();
}

DeleteWrapper *SqlWrappers::deleted(const QString &table) {
    return new DeleteWrapper(table);
}

InsertWrapper *SqlWrappers::insert() {
    return new InsertWrapper();
}

InsertWrapper *SqlWrappers::insert(const QString &table) {
    return new InsertWrapper(table);
}

SelectWrapper *SqlWrappers::select() {
    return new SelectWrapper();
}

SelectWrapper *SqlWrappers::select(const QString &table) {
    return new SelectWrapper(table);
}

SelectWrapper *SqlWrappers::select(const QString &table, const QString &alias) {
    return new SelectWrapper(table, alias);
}
