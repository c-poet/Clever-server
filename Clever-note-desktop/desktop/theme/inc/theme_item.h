// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#ifndef THEME_ITEM_H
#define THEME_ITEM_H

#include <QMap>
#include "theme_file.h"

class Theme;

class ThemeItem {
    friend class Theme;

private:
    // 主题id
    QString id;
    // 主题名称
    QString name;
    // 主题作者
    QString author;
    // 主题首页url
    QString url;
    // 主题描述
    QString description;
    // 主题路径
    QString basePath;
    // 文件列表
    QList<ThemeFile *> *files;
    // 是否已经加载
    bool loaded;
    // 样式表
    QMap<QString, QString> *sheet;

    /// @brief 加载文件内容，类型Css
    void loadByCss(ThemeFile *file);

    /// @brief 加载文件内容，类型Scss
    void loadByScss(ThemeFile *file);

public:
    explicit ThemeItem();

    virtual  ~ThemeItem();

    /// @brief 是否已经加载
    /// @return bool
    bool isLoaded() const;

    /// @brief 加载
    /// @return 是否加载成功
    bool load();

    /// @brief 重新加载
    /// @return 是否加载成功
    bool reload();
};

#endif
