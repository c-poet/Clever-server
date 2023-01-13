// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#ifndef THEME_H
#define THEME_H

#include <QMap>

class Theme {
private:
    QMap<QString, QString> *sheetMapper;

protected:
    /// @brief 样式拷贝
    /// @param sheetMap 样式信息
    void copy(const QMap<QString, QString> *sheetMap);

public:
    Theme();

    virtual ~Theme();

    /// @brief 获取样式
    /// @param key 样式key
    /// @return 样式内容
    QString &operator[](const QString &key) {
        return (*this->sheetMapper)[key];
    }

    /// @brief 查询样式
    /// @param key 样式key
    /// @return 样式内容
    QString &find(const QString &key);

    /// @brief 查询样式并指定默认值
    /// @param key 样式key
    /// @param defaultVal 默认值
    /// @return 样式内容
    QString find(const QString &key, const QString &defaultVal);

    /// @brief 添加样式，将拼接在旧的样式内容上
    /// @param key 样式key
    /// @param value 样式内容
    void add(const QString &key, const QString &value);

    /// @brief 加载主题
    /// @details 默认会从theme目录下加载
    /// @param themeName 主题名称
    void load(const QString &themeName);

    /// @brief 在指定路径查找入口文件
    /// @param basePath 指定路径
    void load4path(const QString &basePath);

    /// @brief 加载样式文件
    /// @param filePath 样式文件路径
    void load4file(const QString &filePath);

    /// @brief 加载样式文件
    /// @param basePath 样式文件所有目录
    /// @param fileName 样式文件名称
    void load4file(const QString &basePath, const QString &fileName);

    /// @brief 删除样式
    /// @param key 样式key
    void del(const QString &key);

    /// @brief 清空所有样式
    void delAll();

    /// @brief 主题样式拷贝
    /// @param other 主题
    void copy(const Theme *other);
};

#endif
