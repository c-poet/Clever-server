// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#ifndef  C_POET_CSS_HANDLE_H
#define  C_POET_CSS_HANDLE_H

#include "css_parse.h"

/// @brief 处理函数类型声明s
typedef void (*Handle)(ParseContext *);

/// @brief 初始化所有处理函数
/// @param context 解析上下文
void initCssHandles(ParseContext *context);

/// @brief 根据当前上下文自动调用处理函数，直到遇到期待的函数或者内容结束
/// @details 如果未设置期待函数，则一直自动调用，直到内容结束
/// @param context 解析上下文
/// @param awaiting 是否是值得等待的
void callAwaitHandle(ParseContext *context, bool awaiting = true);

/// @brief 根据当前上下文自动调用处理函数
/// @param context 解析上下文
void callGlobalHandle(ParseContext *context);

/// @brief 默认处理函数
/// @param context 解析上下文
void handleDefault(ParseContext *context);

/// @brief 处理注释以及取得当前标识key
/// @param context 解析上下文
void handleGloss(ParseContext *context);

/// @brief 处理import语句
/// @param context 解析上下文
void handleImport(ParseContext *context);

/// @brief 处理感叹号及下划线
/// @details 例如：selected:!active可使用selected:_active替代，因此selected:__active会被转义为selected:_active
/// @param context 解析上下文
void handleUnderline(ParseContext *context);

/// @brief 空格处理
/// @param context 解析上下文
void handleSpace(ParseContext *context);

/// @brief 处理回车字符
/// @param context 解析上下文
void handleCrlf(ParseContext *context);

/// @brief 处理tab字符
/// @param context 解析上下文
void handleTab(ParseContext *context);

/// @brief 处理分号
/// @param context 解析上下文
void handleSemicolon(ParseContext *context);

/// @brief 处理左花括号
/// @param context 解析上下文
void handleLeftBrace(ParseContext *context);

/// @brief 处理右花括号
/// @param context 解析上下文
void handleRightBrace(ParseContext *context);

/// @brief 处理冒号
/// @param context 解析上下文
void handleColon(ParseContext *context);

#endif
