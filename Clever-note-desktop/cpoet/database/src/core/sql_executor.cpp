// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/3

#include <QSqlQuery>
#include <QSqlError>
#include <QSqlRecord>
#include "cpoet/core/inc/log.h"
#include "inc/core/sql_executor.h"
#include "inc/core/db_context_holder.h"

QVariantMap *collectMapResult(QSqlQuery *query) {
    if (!query->next()) {
        return nullptr;
    }
    auto *result = new QMap<QString, QVariant>();
    QSqlRecord sqlRecord = query->record();
    int count = sqlRecord.count();
    for (int i = 0; i < count; ++i) {
        (*result)[sqlRecord.fieldName(i)] = query->value(i);
    }
    return result;
}

QList<QVariantMap> *collectListResult(QSqlQuery *query) {
    if (!query->next()) {
        return nullptr;
    }
    auto *result = new QList<QVariantMap>();
    QSqlRecord sqlRecord = query->record();
    int count = sqlRecord.count();
    do {
        QVariantMap map;
        for (int i = 0; i < count; ++i) {
            map[sqlRecord.fieldName(i)] = query->value(i);
        }
        result->append(map);
    } while (query->next());
    return result;
}

int collectNumRowsAffected(QSqlQuery *query) {
    return query->isSelect() ? query->size() : query->numRowsAffected();
}

void handleError(const QString &sql, QSqlQuery *query) {
    QSqlError error = query->lastError();
    qWarning() << "[" << sql << "]执行失败，错误码：" << error.nativeErrorCode() << "原因：" << error.text();
}

SqlExecutor::SqlExecutor() {
    this->_db = QSqlDatabase::database(DbContextHolder::getDbName());
    this->_query = new QSqlQuery(this->_db);
}

SqlExecutor::SqlExecutor(const QSqlDatabase &db) {
    this->_db = db;
    this->_query = new QSqlQuery(this->_db);
}

SqlExecutor::~SqlExecutor() {
    this->_db.close();
    delete this->_query;
}

const QSqlDatabase &SqlExecutor::getDb() const {
    return this->_db;
}

int SqlExecutor::execute(const QString &sql) {
    int result = 0;
    this->execute(sql, [&result](QSqlQuery *query) {
        result = collectNumRowsAffected(query);
    });
    return result;
}

int SqlExecutor::execute(const QString &sql, const QVariantList &params) {
    int result = 0;
    this->execute(sql, params, [&result](QSqlQuery *query) {
        result = collectNumRowsAffected(query);
    });
    return result;
}

int SqlExecutor::execute(const QString &sql, const QVariantMap &params) {
    int result = 0;
    this->execute(sql, params, [&result](QSqlQuery *query) {
        result = collectNumRowsAffected(query);
    });
    return result;
}

void SqlExecutor::execute(const QString &sql, const std::function<void(QSqlQuery *)> &callback) {
    auto *query = this->_query;
    if (query->exec()) {
        callback(query);
    } else {
        handleError(sql, query);
    }
}

void
SqlExecutor::execute(const QString &sql, const QVariantList &params, const std::function<void(QSqlQuery *)> &callback) {
    if (params.isEmpty()) {
        this->execute(sql, callback);
    } else {
        auto *query = this->_query;
        if (query->prepare(sql)) {
            for (const auto &param: params) {
                query->addBindValue(param);
            }
            if (query->exec()) {
                callback(query);
            } else {
                handleError(sql, query);
            }
        } else {
            handleError(sql, query);
        }
    }
}

void
SqlExecutor::execute(const QString &sql, const QVariantMap &params, const std::function<void(QSqlQuery *)> &callback) {
    if (params.isEmpty()) {
        this->execute(sql, callback);
    } else {
        auto *query = this->_query;
        if (query->prepare(sql)) {
            QList<QString> keys = params.keys();
            for (const auto &key: keys) {
                query->bindValue(key, params[key]);
            }
            if (query->exec()) {
                callback(query);
            } else {
                handleError(sql, query);
            }
        } else {
            handleError(sql, query);
        }
    }
}

QVariantMap *SqlExecutor::findOne(const QString &sql) {
    QVariantMap *result = nullptr;
    this->execute(sql, [&result](QSqlQuery *query) {
        result = collectMapResult(query);
    });
    return result;
}

QVariantMap *SqlExecutor::findOne(const QString &sql, const QVariantList &params) {
    QVariantMap *result = nullptr;
    this->execute(sql, params, [&result](QSqlQuery *query) {
        result = collectMapResult(query);
    });
    return result;
}

QVariantMap *SqlExecutor::findOne(const QString &sql, const QVariantMap &params) {
    QVariantMap *result = nullptr;
    this->execute(sql, params, [&result](QSqlQuery *query) {
        result = collectMapResult(query);
    });
    return result;
}

QList<QVariantMap> *SqlExecutor::findList(const QString &sql) {
    QList<QVariantMap> *result = nullptr;
    this->execute(sql, [&result](QSqlQuery *query) {
        result = collectListResult(query);
    });
    return result;
}

QList<QVariantMap> *SqlExecutor::findList(const QString &sql, const QVariantList &params) {
    QList<QVariantMap> *result = nullptr;
    this->execute(sql, params, [&result](QSqlQuery *query) {
        result = collectListResult(query);
    });
    return result;
}

QList<QVariantMap> *SqlExecutor::findList(const QString &sql, const QVariantMap &params) {
    QList<QVariantMap> *result = nullptr;
    this->execute(sql, params, [&result](QSqlQuery *query) {
        result = collectListResult(query);
    });
    return result;
}
