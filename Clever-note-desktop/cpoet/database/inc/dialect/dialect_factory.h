// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/1

#ifndef C_POET_DIALECT_FACTORY_H
#define C_POET_DIALECT_FACTORY_H

#include <QMap>
#include "dialect.h"

/// @brief 方言处理factory
class DialectFactory {
private:
    // 全局静态实例对象
    static DialectFactory INSTANCE;

    // 方言支持集合
    QMap<QString, Dialect *> *_dialects;

    // 构造私有化
    DialectFactory();

public:

    ~DialectFactory();

    /// @brief 获取方言支持
    /// @param dialectName 方言名称
    /// @return 方言翻译实例
    Dialect *getDialect(const QString &dialectName);

    /// @brief 注册方言
    /// @param dialectName 方言名称
    /// @param dialect 方言处理器实例
    void registerDialect(const QString &dialectName, Dialect *dialect);

    /// @brief 移出并释放方言
    /// @param dialectName 方言名称
    /// @return 是否移出成功
    bool removeDialect(const QString &dialectName);

    /// @brief 获取方言工厂实例
    /// @return 方言工厂实例
    static DialectFactory &getInstance();

    /// @brief 获取方言支持
    /// @param dialectName 方言名称
    /// @return 方言翻译实例
    static Dialect *get(const QString &dialectName);

    /// @brief 进行方言翻译
    /// @param dialectName 方言名称
    /// @param wrapper Sql包装
    /// @return 翻译结果
    /// @throw Exception 方言不存在时将抛出异常
    static DialectResult *dialect(const QString &dialectName, SqlWrapper *wrapper) noexcept(false);

    /// @brief 静默进行方言翻译
    /// @param dialectName 方言名称
    /// @param wrapper Sql包装
    /// @return 翻译结果
    static DialectResult *dialectSilence(const QString &dialectName, SqlWrapper *wrapper);
};

#endif
