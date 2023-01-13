// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/31

#ifndef C_POET_SQL_WRAPPER_H
#define C_POET_SQL_WRAPPER_H

#include <functional>
#include "sql_wrapper_types.h"

/// @brief 数据库操作wrapper
class SqlWrapper {
public:
    virtual ~SqlWrapper() = default;

    /// @brief 获取Wrapper类型
    /// @return Wrapper类型
    virtual SqlWrapperTypes type() = 0;
};

#endif
