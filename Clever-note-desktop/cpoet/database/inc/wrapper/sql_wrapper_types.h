// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/31

#ifndef C_POET_SQL_WRAPPER_TYPES_H
#define C_POET_SQL_WRAPPER_TYPES_H

/// @brief 常用Wrapper类型定义
enum class SqlWrapperTypes {
    /// @brief 未定义，普通类
    NONE,

    /// @brief 查询类
    QUERY,

    /// @brief 更新类
    UPDATE,

    /// @brief 新增类
    INSERT,

    /// @brief 删除类
    DELETE,
};
#endif
