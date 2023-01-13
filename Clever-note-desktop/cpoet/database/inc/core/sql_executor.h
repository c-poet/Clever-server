// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/3

#ifndef C_POET_SQL_EXECUTOR_H
#define C_POET_SQL_EXECUTOR_H

#include <QSqlDatabase>
#include <functional>

class QVariant;

typedef QMap<QString, QVariant> QVariantMap;
typedef QList<QVariant> QVariantList;

/// @brief Sql执行器
class SqlExecutor {
private:
    QSqlQuery *_query;
protected:
    QSqlDatabase _db;
public:
    SqlExecutor();

    explicit SqlExecutor(const QSqlDatabase &db);

    virtual ~SqlExecutor();

    const QSqlDatabase &getDb() const;

    int execute(const QString &sql);

    int execute(const QString &sql, const QVariantList &params);

    int execute(const QString &sql, const QVariantMap &params);

    void execute(const QString &sql, const std::function<void(QSqlQuery *)> &callback);

    void execute(const QString &sql, const QVariantList &params, const std::function<void(QSqlQuery *)> &callback);

    void execute(const QString &sql, const QVariantMap &params, const std::function<void(QSqlQuery *)> &callback);

    QVariantMap *findOne(const QString &sql);

    QVariantMap *findOne(const QString &sql, const QVariantList &params);

    QVariantMap *findOne(const QString &sql, const QVariantMap &params);

    QList<QVariantMap> *findList(const QString &sql);

    QList<QVariantMap> *findList(const QString &sql, const QVariantList &params);

    QList<QVariantMap> *findList(const QString &sql, const QVariantMap &params);
};

#endif
