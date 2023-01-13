// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include "inc/css/css_parse.h"
#include "inc/css/css_handle.h"
#include "cpoet/core/inc/core.h"
#include <QFileInfo>

//----------------------------------------------------------------------------------------------------------------------
// 解析Css定制语法
//----------------------------------------------------------------------------------------------------------------------

/// @brief 创建解析上下文
/// @param css 需要解析的css
/// @param basePath 相对路径前缀
/// @param parent 指定上下文父级
/// @return 解析上下文
ParseContext *createParseContext(const QString &css, const QString &basePath, const ParseContext *parent = nullptr) {
    auto context = new ParseContext();
    context->css = css;
    context->len = css.size();
    context->basePath = basePath;
    if (parent == nullptr) {
//        context->key = GLOBAL_CSS_KEY;
        context->key = "";
        context->result = new QMap<QString, QString>();
        // 初始化处理函数
        initCssHandles(context);
    } else {
        context->parent = parent;
        context->key = parent->key;
        context->content = parent->content;
        context->defaultHandle = parent->defaultHandle;
        context->handles = parent->handles;
        context->result = parent->result;
    }
    return context;
}

/// @brief 释放上下文
/// @param context 解析上下文
void freeParseContext(ParseContext *context) {
    if (context->parent == nullptr) {
        delete context->handles;
    }
    delete context;
}

/// @brief 解析css
/// @param css 需要解析的css内容
/// @param basePath 默认根路径
/// @param parent 指定上下文父级
QMap<QString, QString> *parseCss(const QString &css, const QString &basePath, ParseContext *parent) {
    ParseContext *parseContext = createParseContext(css, basePath, parent);
    // 调用解析方法进行css解析
    callAwaitHandle(parseContext);
    // 判断是否需要做最后的添加处理
    if (parseContext->parent == nullptr && !parseContext->content.isEmpty()) {
        (*parseContext->result)[parseContext->key].append(parseContext->content);
    }
    // 获取最终解析结果并返回
    QMap<QString, QString> *result = parseContext->result;
    freeParseContext(parseContext);
    return result;
}

/// @brief 指定文件进行css解析
/// @param file 文件路径
/// @param basePath import找不到时默认拼接的前缀
/// @param parent 指定上下文父级
QMap<QString, QString> *parseCss4path(const QString &file, const QString &basePath, ParseContext *parent) {
    auto qFile = new QFile(file);
    QString result = readFile(qFile);
    delete qFile;
    return parseCss(result, basePath, parent);
}

//----------------------------------------------------------------------------------------------------------------------
// 对外透明方法实现
//----------------------------------------------------------------------------------------------------------------------
QString readFile(QFile *file) {
    QString result;
    if (file->open(QIODevice::ReadOnly)) {
        auto *qts = new QTextStream(file);
        result = qts->readAll();
        delete qts;
        file->close();
    }
    return result;
}

QString getAbsolutePath(const QString &filePath) {
    auto *fileInfo = new QFileInfo(filePath);
    QString result = fileInfo->absolutePath();
    delete fileInfo;
    return result;
}

QMap<QString, QString> *parseCss(const QString &css, const QString &basePath) {
    return parseCss(css, basePath, nullptr);
}

QMap<QString, QString> *parseCss4path(const QString &file, const QString &basePath) {
    return parseCss4path(file, basePath, nullptr);
}
