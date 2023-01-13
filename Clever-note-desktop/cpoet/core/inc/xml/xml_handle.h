// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#ifndef C_POET_XML_HANDLE_H
#define C_POET_XML_HANDLE_H

#include <QMap>
#include <QStringRef>
#include <QXmlStreamReader>
#include "xml_parser.h"

/// @brief xml处理上下文 
struct XmlHandleContext {
    // 当前解析key的前缀
    QString prefix;
    // 解析配置
    XmlParseConfig config;
    // 重复的key统计，方便生成序号
    QMap<QStringRef, int> *duplicateKeyCount = nullptr;
    // 处理函数集合
    QMap<QXmlStreamReader::TokenType, void *> *handles = nullptr;

    /// @brief 拼接前缀
    /// @param suffix 后缀
    /// @return 拼接结果
    QString joinPrefix(const QString &suffix) const;
};

/// @brief 读取xml文件 
/// @param content xml内容
/// @param config 配置
/// @return 解析结果
QMap<QString, QString> *handleXmlRead(const QString &content, const XmlParseConfig &config);

/// @brief 默认处理方法，读取入口方法 
/// @param reader xml
/// @param config 解析配置
/// @param carry 携带返回结果
void startHandleRead(QXmlStreamReader *reader, XmlHandleContext *context, QMap<QString, QString> *carry);

/// @brief 处理document
/// @param reader xml
/// @param config 解析配置
/// @param carry 携带返回结果
void handleDocument(QXmlStreamReader *reader, XmlHandleContext *context, QMap<QString, QString> *carry);

/// @brief 处理element
/// @param reader xml
/// @param config 解析配置
/// @param carry 携带返回结果
void handleElement(QXmlStreamReader *reader, XmlHandleContext *context, QMap<QString, QString> *carry);

/// @brief 处理Characters
/// @param reader xml
/// @param config 解析配置
/// @param carry 携带返回结果
void handleCharacters(QXmlStreamReader *reader, XmlHandleContext *context, QMap<QString, QString> *carry);

#endif
