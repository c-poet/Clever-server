// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/31

#ifndef C_POET_SQL_CONDITION_TYPES_H
#define C_POET_SQL_CONDITION_TYPES_H

/// @brief 表达类型
enum class SqlConditionTypes {
    /// 未定义
    NONE,
    /// @brief 为NULL
    IS_NULL,
    /// @brief 不为NULL
    NOT_NULL,
    /// @brief 等于
    EQ,
    /// @brief 不等于
    NEQ,
    /// @brief 大于
    GT,
    /// @brief 大于等于
    GE,
    /// @brief 小于
    LT,
    /// @brief 小于等于
    LE,
    /// @brief Wrapper类型
    WRAPPER
};

#endif
