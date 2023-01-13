// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/3

#ifndef C_POET_WRAPPER_EXECUTOR_H
#define C_POET_WRAPPER_EXECUTOR_H

#include "../core/sql_executor.h"

class DialectResult;

class SqlWrapper;

class SelectWrapper;

/// @brief Wrapper执行器
class WrapperExecutor : public SqlExecutor {
public:
    /// @brief 执行wrapper并返加受影响的条数
    /// @param wrapper 执行的wrapper
    /// @return 受影响的条数
    int execute(SqlWrapper *wrapper);

    void execute(SqlWrapper *wrapper, const std::function<void(DialectResult *)> &callback);

    QVariantMap *findOne(SelectWrapper *wrapper);

    QList<QVariantMap> *findList(SelectWrapper *wrapper);
};

#endif
