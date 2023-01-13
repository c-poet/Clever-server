// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#ifndef C_POET_CSS_H
#define C_POET_CSS_H

#include <QMap>

/// @brief Css解析工具类
/// @author CPoet
class CssParser {
public:
    /// @brief 指定文件路径并解析css
    /// @param css文件路径
    /// @return 解析结果
    static QMap<QString, QString> *parse4path(const QString &filePath);

    /// @brief 指定文件路径并解析css
    /// @param basePath css文件所在路径
    /// @param fileName css文件名
    /// @return 解析结果
    static QMap<QString, QString> *parse4path(const QString &basePath, const QString &fileName);

    /// @brief 解析css内容
    /// @param css
    /// @return 解析结果
    static QMap<QString, QString> *parse(const QString &css);

    /// @brief 解析css内容并指定根路径
    /// @param css css内容
    /// @param basePath 找不到链接的css时，拼接的路径前缀
    /// @return
    static QMap<QString, QString> *parse(const QString &css, const QString &basePath);
};

#endif
