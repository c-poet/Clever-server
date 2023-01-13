// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/1

#include "inc/sqlite/sqlite_dialect.h"
#include "inc/sqlite/sqlite_dialect_handler.h"

DialectResult *SqliteDialect::unwrap(DialectContext *context) {
    auto *result = new DialectResult();
    auto *handler = new SqliteDialectHandler(context, result);
    handler->exec();
    delete handler;
    return result;
}
