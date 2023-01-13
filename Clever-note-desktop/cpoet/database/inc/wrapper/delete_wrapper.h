// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/31

#ifndef C_POET_DELETE_WRAPPER
#define C_POET_DELETE_WRAPPER

#include "where_wrapper.h"

/// @brief 删除Wrapper
class DeleteWrapper : public WhereWrapper<DeleteWrapper> {
private:
    QString _table;

public:
    DeleteWrapper();

    /// @brief 指定表名
    /// @param table 表名
    explicit DeleteWrapper(const QString &table);

    /// @brief 获取表名
    /// @return 表名
    const QString &getTable() const;

    /// @brief 设置表名
    /// @param table 表名
    /// @return this 实例
    DeleteWrapper *setTable(const QString &table);

    SqlWrapperTypes type() override {
        return SqlWrapperTypes::DELETE;
    }
};

#endif
