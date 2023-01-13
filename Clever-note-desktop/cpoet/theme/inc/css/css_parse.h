// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#ifndef C_POET_CSS_PARSE_H
#define C_POET_CSS_PARSE_H

#include <QFile>

/// @brief 解析上下文定义
struct ParseContext {
    // 父级解析上下文
    const ParseContext *parent = nullptr;

    // 当前指针位置
    int index = 0;
    // 当前处理的key
    QString key;
    // 当前处理的内容
    QString content;
    // 存放最终返回结果
    QMap<QString, QString> *result = nullptr;

    // css长度
    int len = 0;
    // 需要解析的css
    QString css;
    // 相对路径前缀
    QString basePath;

    // 默认处理函数
    void *defaultHandle = nullptr;

    // 上个处理函数
    void *preHandle = nullptr;

    // 期待下一个处理函数
    void *awaitHandle = nullptr;

    // 处理函数集合
    QMap<QChar, void *> *handles = nullptr;
};

/// @brief 读取文件内容
/// @param file 文件
/// @return 文件内容
QString readFile(QFile *file);

/// @brief 获取文件所在路径
/// @param filePath 文件路径
/// @return 文件所在路径
QString getAbsolutePath(const QString &filePath);

/// @brief 解析css
/// @param css css内容
/// @param basePath 路径前缀，存在@import语句时用于拼接相对路径
/// @return 解析结果，可能返回null
QMap<QString, QString> *parseCss(const QString &css, const QString &basePath = "");

/// @brief 解析css
/// @param file css文件
/// @param basePath 相对路径前缀
/// @return 解析结果或者null
QMap<QString, QString> *parseCss4path(const QString &file, const QString &basePath);

#endif
