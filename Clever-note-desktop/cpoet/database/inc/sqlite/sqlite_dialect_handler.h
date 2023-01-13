// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/2

#ifndef C_POET_SQLITE_DIALECT_HANDLER_H
#define C_POET_SQLITE_DIALECT_HANDLER_H

#include "../dialect/dialect_result.h"
#include "../dialect/dialect_context.h"

/// @brief 方言翻译
class SqliteDialectHandler {
private:
    DialectResult *_result;
    DialectContext *_context;

    /// @brief 获取指定类型的Wrapper
    /// @return 指定类型的Wrapper
    template<class W>
    W *getTargetTypeWrapper();

public:
    /// @brief 构造
    /// @param context 翻译上下文
    /// @param result 翻译结果
    SqliteDialectHandler(DialectContext *context, DialectResult *result);

    /// @brief 执行翻译
    void exec();
};

#endif
