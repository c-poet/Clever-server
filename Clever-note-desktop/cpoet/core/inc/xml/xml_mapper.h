// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#ifndef C_POET_XML_MAPPER_H
#define C_POET_XML_MAPPER_H

#include <QMap>
#include <QList>

class XmlMapper {
private:
    QMap<QString, QString> *mapper;

public:
    explicit XmlMapper(QMap<QString, QString> *mapper);

    virtual ~XmlMapper();

    /// @brief 获取所有的键值对
    /// @details 内容已经释放该对象，尽量不要修改该对象
    /// @return 所有存在的键值对
    const QMap<QString, QString> *getAllMapper();

    /// @brief 判断是否为空
    /// @return bool
    bool isEmpty();

    /// @brief 获取键值
    /// @param key 键名
    /// @return 键值
    QString get(const QString &key);

    /// @brief 获取键值
    /// @param key 键名
    /// @param defaultVal 默认值
    /// @return int类型值
    QString get(const QString &key, const QString &defaultVal);

    /// @brief 获取long类型值
    /// @param key 键名
    /// @return long类型值
    long getAsLong(const QString &key);

    /// @brief 获取long类型值
    /// @param key 键名
    /// @param defaultVal 键值
    /// @return long类型值
    long getAsLong(const QString &key, long defaultVal);

    /// @brief 获取int类型值
    /// @param key 键名
    /// @return int类型值
    int getAsInt(const QString &key);

    /// @brief 获取int类型值
    /// @param key 键名
    /// @param defaultVal 默认值
    /// @return int类型值
    int getAsInt(const QString &key, int defaultVal);

    /// @brief 获取float类型值
    /// @param key 键名
    /// @return float类型值
    float getAsFloat(const QString &key);

    /// @brief 获取float类型值
    /// @param key 键名
    /// @param defaultVal 默认值
    /// @return float类型值
    float getAsFloat(const QString &key, float defaultVal);

    /// @brief 获取double类型值
    /// @param key 键名
    /// @return double类型值
    double getAsDouble(const QString &key);

    /// @brief 获取double类型的值
    /// @param key 键名
    /// @param defaultVal 默认值
    /// @return double类型值
    double getAsDouble(const QString &key, double defaultVal);

    /// @brief 获取bool类型的值
    /// @param key 键名
    /// @return bool类型值
    bool getAsBool(const QString &key);

    /// @brief 获取bool类型的值
    /// @param key 键名
    /// @param defaultVal 默认值
    /// @return bool类型值
    bool getAsBool(const QString &key, bool defaultVal);

    /// @brief 根据前缀获取键值对
    /// @param key 键名前缀
    /// @return 符合条件的键值对
    QMap<QString, QString> *getAsMap(const QString &key);

    /// @brief 根据前缀获取值列表
    /// @param key 键名前缀
    /// @return 符合条件的值列表
    QList<QString> *getAsList(const QString &key);
};

#endif
