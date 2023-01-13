// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/2

#ifndef C_POET_SQL_CONDITION_HEADS_H
#define C_POET_SQL_CONDITION_HEADS_H

/// @brief Sql表达式头信息
enum class SqlConditionHeads {
    /// @brief 一元表达式
    UNARY,
    /// @brief 二元表达式
    BINARY,
    /// @brief 三元表达式
    TERNARY,
    /// @brief Wrapper
    WRAPPER,
    /// @brief 逻辑表达式 
    LOGIC,
    /// @brief 域表达式，例如括号
    REGION
};

#endif
