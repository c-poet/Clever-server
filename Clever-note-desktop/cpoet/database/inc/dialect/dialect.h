// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/1

#ifndef C_POET_DIALECT_H
#define C_POET_DIALECT_H

#include "dialect_context.h"
#include "dialect_result.h"

/// @brief 方言翻译
class Dialect {
public:
    /// @brief 析构
    virtual ~Dialect() = default;

    /// @brief 完成翻译
    /// @param context 翻译上下文
    /// @return 翻译结果
    virtual DialectResult *unwrap(DialectContext *context) = 0;
};

#endif
