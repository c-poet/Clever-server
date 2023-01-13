// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/3

#include <QFileInfo>
#include "cpoet/core/inc/log.h"
#include "cpoet/database/inc/core/sql_executor.h"
#include "cpoet/database/inc/core/db_context_holder.h"

#define DB_NAME "notebook"
#define DB_FILE_NAME "test.db"

int main(int argc, char *argv[]) {
    QString dbPath = QFileInfo(__FILE__).absolutePath() + "/" + DB_FILE_NAME;
    DbContextHolder::setDefaultDbName(DB_NAME);
    QSqlDatabase db = QSqlDatabase::addDatabase("QSQLITE", DB_NAME);
    db.setDatabaseName(dbPath);
    auto *executor = new SqlExecutor();
    auto *result = executor->findOne("SELECT * FROM user WHERE name = ?", {"cpoet"});
    if (result != nullptr) {
        qWarning() << "查询结果：" << (*result);
        delete result;
    }
    delete executor;
    return 0;
}
