// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/3

#include "inc/core/db_context_holder.h"

QString DbContextHolder::_defaultDbName = DCH_DEFAULT_DB_NAME; /* NOLINT */
QThreadStorage<QString> DbContextHolder::_threadStorage; /* NOLINT */

void DbContextHolder::setDefaultDbName(const QString &dbName) {
    if (!dbName.isEmpty()) {
        DbContextHolder::_defaultDbName = dbName;
    }
}

QString DbContextHolder::getDefaultDbName() {
    return DbContextHolder::_defaultDbName;
}

QString DbContextHolder::getDbName() {
    return DbContextHolder::_threadStorage.hasLocalData()
           ? DbContextHolder::_threadStorage.localData()
           : DbContextHolder::getDefaultDbName();
}

void DbContextHolder::setDbName(const QString &dbName) {
    DbContextHolder::_threadStorage.setLocalData(dbName);
}
