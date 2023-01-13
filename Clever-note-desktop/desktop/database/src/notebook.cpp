// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include "inc/notebook.h"
#include "cpoet/core/inc/core.h"
#include <QSqlDatabase>
#include <QSqlQuery>
#include <QSqlResult>
#include <QSqlRecord>

namespace Dao {
    void createNotebook() {
        QString sql = "CREATE TABLE clever_notebook(id int primary key, name varchar(68))";
        QSqlDatabase db = QSqlDatabase::addDatabase("QSQLITE", "notebook");
        qDebug() << "dbPath: " << getApplicationFilePath("data/notebook.db");
        db.setDatabaseName(getApplicationFilePath("data/notebook.db"));
        QString name = db.connectionName();
        if (db.open()) {
            auto *query = new QSqlQuery(db);
            query->exec(sql);
            query->bindValue("2", 2);
            query->bindValue("3", "123");
            delete query;
            db.close();
        }
        QSqlDatabase::removeDatabase(name);
    }

    void listNotebook() {
        QString sql = "SELECT * FROM clever_notebook";
        QSqlDatabase db = QSqlDatabase::addDatabase("QSQLITE");
        db.setDatabaseName(getApplicationFilePath("data/notebook.db"));
        QString name = db.connectionName();
        if (db.open()) {
            auto *query = new QSqlQuery(db);
            query->exec(sql);
            while (query->nextResult()) {
                auto *result = query->result();
            }
            delete query;
        }
        QSqlDatabase::removeDatabase(name);
    }
}
