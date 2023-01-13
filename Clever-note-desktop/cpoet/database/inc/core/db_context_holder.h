// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/3

#ifndef C_POET_DB_CONTEXT_HOLDER_H
#define C_POET_DB_CONTEXT_HOLDER_H

#include <QString>
#include <QThreadStorage>

#define DCH_DEFAULT_DB_NAME "_default"

/// @brief 当前db名称上下文记录
class DbContextHolder {
private:
    // 默认db名称
    static QString _defaultDbName;
   
    // 线程Storage
    static QThreadStorage<QString> _threadStorage;

    DbContextHolder() = default;

public:
    /// @brief 设置默认db名称
    /// @param dbName 默认db名称
    static void setDefaultDbName(const QString &dbName);

    /// @brief 获取默认db名称
    /// @return 默认db名称
    static QString getDefaultDbName();

    /// @brief 获取当前线程指定的Db名称，未设置则返回默认名名
    /// @return 当前线程指定Db名称
    static QString getDbName();

    /// @brief 设置当前线程Db名称
    /// @param dbName Db名称
    static void setDbName(const QString &dbName);
};

#endif
