// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/1

#ifndef C_POET_SQLITE_DIALECT_H
#define C_POET_SQLITE_DIALECT_H

#include "../dialect/dialect.h"

/// @brief Sqlite方言
class SqliteDialect : public Dialect {
public:
    DialectResult *unwrap(DialectContext *context) override;
};

#endif
