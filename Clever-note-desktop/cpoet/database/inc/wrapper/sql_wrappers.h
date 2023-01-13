// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/1

#ifndef C_POET_SQL_WRAPPERS_H
#define C_POET_SQL_WRAPPERS_H

#include "delete_wrapper.h"
#include "update_wrapper.h"
#include "insert_wrapper.h"
#include "select_wrapper.h"

class SqlWrappers {
public:
    static UpdateWrapper *update();

    static UpdateWrapper *update(const QString &table);

    static DeleteWrapper *deleted();

    static DeleteWrapper *deleted(const QString &table);

    static InsertWrapper *insert();

    static InsertWrapper *insert(const QString &table);

    static SelectWrapper *select();

    static SelectWrapper *select(const QString &table);

    static SelectWrapper *select(const QString &table, const QString &alias);
};

#endif
