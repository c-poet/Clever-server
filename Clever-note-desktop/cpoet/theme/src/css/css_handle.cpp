// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include "inc/css/css_handle.h"
#include "inc/css/css_handle_func.h"
#include "cpoet/core/inc/core.h"

void initCssHandles(ParseContext *context) {
    if (context->handles == nullptr) {
        context->handles = new QMap<QChar, void *>;
    }
    // 注册默认处理函数
    context->defaultHandle = (void *) handleDefault;
    // 注册所有处理函数
    (*(context->handles))['/'] = (void *) handleGloss;
    (*(context->handles))['@'] = (void *) handleImport;
    (*(context->handles))['_'] = (void *) handleUnderline;
    (*(context->handles))[' '] = (void *) handleSpace;
    (*(context->handles))['\r'] = (void *) handleCrlf;
    (*(context->handles))['\n'] = (void *) handleCrlf;
    (*(context->handles))['\t'] = (void *) handleTab;
    (*(context->handles))[';'] = (void *) handleSemicolon;
    (*(context->handles))['{'] = (void *) handleLeftBrace;
    (*(context->handles))['}'] = (void *) handleRightBrace;
    (*(context->handles))[':'] = (void *) handleColon;
}

void callAwaitHandle(ParseContext *context, bool awaiting) {
    // 不期待的handle，则直接调用
    if (context->awaitHandle == nullptr) {
        callGlobalHandle(context);
    } else {
        // 保留期待函数副本
        void *awaitHandle = context->awaitHandle;
        context->awaitHandle = nullptr;
        while (context->index < context->len) {
            QChar c = context->css[context->index];
            auto targetHandle = (Handle) (*context->handles)[c];
            if (targetHandle == nullptr) {
                targetHandle = (Handle) context->defaultHandle;
            }
            // 目标函数是期待函数则直接返回
            if (awaiting && awaitHandle == targetHandle || !awaiting && awaitHandle != targetHandle) {
                // 带回期待函数的信息
                context->awaitHandle = (void *) targetHandle;
                break;
            }
            // 否则继续调用，直至结束
            targetHandle(context);
        }
    }
}

void callGlobalHandle(ParseContext *context) {
    // 循环调用各处理函数进行处理
    while (context->index < context->len) {
        QChar c = context->css[context->index];
        auto targetHandle = (Handle) (*context->handles)[c];
        // 不存在目标处理函数则调用默认处理
        if (targetHandle == nullptr) {
            targetHandle = (Handle) context->defaultHandle;
        }
        // 调用目标处理函数
        targetHandle(context);
    }
}

void handleDefault(ParseContext *context) {
    context->content.append(context->css[context->index]);
    ++(context->index);
    context->preHandle = (void *) handleDefault;
}

void handleGloss(ParseContext *context) {
    if (context->index + 1 < context->len && context->css[context->index + 1] == '*') {
        int i = context->index + 2;
        if (i < context->len) {
            // 取得CSS-key
            if (context->css[i] == '!') {
                QString key;
                while (++i < context->len) {
                    if (context->css[i] != ' ') {
                        if (context->css[i] == '*') {
                            if (i + 1 < context->len && context->css[i + 1] == '/') {
                                i += 2;
                                break;
                            } else {
                                key.append(context->css[i]);
                            }
                        } else {
                            key.append(context->css[i]);
                        }
                    }
                }
                if (key.isEmpty()) {
//                    key = GLOBAL_CSS_KEY;
                    key = "";
                }
                if (context->key != key) {
                    (*(context->result))[context->key].append(context->content);
                    context->key = key;
                    context->content.clear();
                }
            } else {
                // 去掉注释部分
                while (++i < context->len) {
                    if (context->css[i] == '*' && i + 1 < context->len && context->css[i + 1] == '/') {
                        i += 2;
                        break;
                    }
                }
            }
        }
        context->index = i;
    }
}

/// @brief 加载import的内容
/// @param file 处理的文件名
/// @param context 解析上下文
void loadImportContent(QString &file, ParseContext *context) {
    // 声明解析函数
    QMap<QString, QString> *parseCss4path(const QString &file, const QString &basePath, ParseContext *parent);
    QString filePath = getFitFilePath(context->basePath, file);
    qDebug() << "css解析加载import内容：" << filePath;
    parseCss4path(filePath, context->basePath, context);
}

void handleImport(ParseContext *context) {
    int i = context->index + 1;
    if (isMathCurrentWordIndex(i, context->css, "import")) {
        context->index = i;
        // 处理import后面的空格
        context->preHandle = (void *) handleImport;
        context->awaitHandle = (void *) handleSpace;
        callAwaitHandle(context, false);
        if (context->index < context->len) {
            i = context->index;
            if (context->css[i] == '"') {
                QString file;
                while (++i < context->len && context->css[i] != '"') {
                    file.append(context->css[i]);
                }
                if (i < context->len && context->css[i] == '"' && !file.isEmpty()) {
                    context->index = ++i;
                    loadImportContent(file, context);
                    context->preHandle = (void *) handleImport;
                    context->awaitHandle = (void *) handleSemicolon;
                    callAwaitHandle(context);
                    if (context->awaitHandle == handleSemicolon) {
                        ++(context->index);
                    }
                } else {
                    qDebug() << "import语句存在缺失";
                }
            } else {
                qDebug() << "import语句缺少存在缺失";
            }
        }
    } else {
        context->content.append(context->css[context->index]);
        ++(context->index);
    }
    context->preHandle = (void *) handleImport;
}

void handleUnderline(ParseContext *context) {
    // __为转义符，替换为_
    if (context->index + 1 < context->len && context->css[context->index + 1] == '_') {
        context->content.append('_');
    } else {
        context->content.append('!');
    }
    ++(context->index);
    context->preHandle = (void *) handleUnderline;
}

void handleSpace(ParseContext *context) {
    // 如果没有被特殊处理
    if (context->preHandle == handleDefault) {
        context->preHandle = (void *) handleSpace;
        context->awaitHandle = (void *) handleSpace;
        ++(context->index);
        callAwaitHandle(context, false);
        if (context->awaitHandle == handleDefault) {
            context->content.append(' ');
        }
    } else {
        ++(context->index);
    }
    context->preHandle = (void *) handleSpace;
}

void handleCrlf(ParseContext *context) {
    ++(context->index);
    context->preHandle = (void *) handleCrlf;
}

void handleTab(ParseContext *context) {
    ++(context->index);
    context->preHandle = (void *) handleTab;
}

void handleSemicolon(ParseContext *context) {
    context->content.append(context->css[context->index]);
    ++(context->index);
    context->preHandle = (void *) handleSemicolon;
}

void handleLeftBrace(ParseContext *context) {
    context->content.append(context->css[context->index]);
    ++(context->index);
    context->preHandle = (void *) handleLeftBrace;
}

void handleRightBrace(ParseContext *context) {
    context->content.append(context->css[context->index]);
    ++(context->index);
    context->preHandle = (void *) handleRightBrace;
}

void handleColon(ParseContext *context) {
    context->content.append(context->css[context->index]);
    ++(context->index);
    context->preHandle = (void *) handleRightBrace;
}
