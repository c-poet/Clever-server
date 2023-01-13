// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/2

#ifndef C_POET_SQLITE_DIALECT_FUNC_H
#define C_POET_SQLITE_DIALECT_FUNC_H

#include "../dialect/dialect_result.h"
#include "../wrapper/select_wrapper.h"
#include "../wrapper/insert_wrapper.h"
#include "../wrapper/update_wrapper.h"
#include "../wrapper/delete_wrapper.h"
#include "../dialect/dialect_context.h"

/// @brief 处理SelectWrapper
/// @param wrapper SelectWrapper
/// @param result 返回结果
/// @param context 上下文
void handleSelect(SelectWrapper *wrapper, DialectResult *result, DialectContext *context);

/// @brief 处理InsertWrapper
/// @param wrapper InsertWrapper
/// @param result 返回结果
/// @param context 上下文
void handleInsert(InsertWrapper *wrapper, DialectResult *result, DialectContext *context);

/// @brief 处理UpdateWrapper
/// @param wrapper UpdateWrapper
/// @param result 返回结果
/// @param context 上下文
void handleUpdate(UpdateWrapper *wrapper, DialectResult *result, DialectContext *context);

/// @brief 处理DeleteWrapper
/// @param wrapper DeleteWrapper
/// @param result 返回结果
/// @param context 上下文
void handleDelete(DeleteWrapper *wrapper, DialectResult *result, DialectContext *context);

/// @brief 处理WhereWrapper
/// @param wrapper WhereWrapper
/// @param result 返回结果
/// @param context 上下文
template<class W>
void handleWhere(WhereWrapper<W> *wrapper, DialectResult *result, DialectContext *context);

/// @brief 处理OrderWrapper
/// @param wrapper OrderWrapper
/// @param result 返回结果
/// @param context 上下文
template<class W>
void handleOrder(OrderWrapper<W> *wrapper, DialectResult *result, DialectContext *context);

#endif
