// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#ifndef C_POET_CSS_HANDLE_FUNC_H
#define C_POET_CSS_HANDLE_FUNC_H

#include <QString>

/// @brief 获取从当前位置开始的第一个单词
/// @param i 起始位置
/// @param str 需要查找的字符串
QString getCurrentWord(int i, const QString &str);

/// @brief 获取从当前位置开始的第一个单词，下标同时移动
/// @param i 起始位置
/// @param str 需要查找的字符串
QString getCurrentWordIndex(int &i, const QString &str);

/// @brief 判断从起始位置开始的第一个单词是否和指定单词匹配
/// @param i 起始位置
/// @param str 需要查找的字符串
/// @param word 需要匹配的单词
bool isMathCurrentWord(int i, const QString &str, const QString &word);

/// @brief 判断从起始位置开始的第一个单词是否和指定单词匹配，下标同时移动
/// @param i 起始位置
/// @param str 需要查找的字符串
/// @param word 需要匹配的单词
bool isMathCurrentWordIndex(int &i, const QString &str, const QString &word);

#endif
