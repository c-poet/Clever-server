// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#ifndef C_POET_XML_PARSER_H
#define C_POET_XML_PARSER_H

#include "xml_mapper.h"

struct XmlParseConfig {
    // 分隔符
    QString separator = ".";
    // Text文件使用的Key
    QString textKey = "value";
    // 生成的key统一前缀
    QString prefix;
};

/// @brief xml解析
/// @note 解析结果为K-V对形式，方便调用
/// @author CPoet
class XmlParser {
public:
    /// @brief 解析文件
    /// @param file 文件路径
    /// @return 解析结果
    static XmlMapper *parseFile(const QString &file);

    /// @brief 解析xml，使用默认配置
    /// @param content 待解析的内容
    /// @return 解析结果
    static XmlMapper *parse(const QString &content);

    /// @brief 解析xml
    /// @param 待解析的xml内容 
    /// @param config 解析配置
    /// @return 解析结果，K-V对形式
    static XmlMapper *parse(const QString &content, const XmlParseConfig &config);
};

#endif
