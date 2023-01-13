// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/4

#include <QFileInfo>
#include <QDateTime>
#include <QUuid>
#include "cpoet/core/inc/log.h"
#include "cpoet/database/inc/core/db_context_holder.h"
#include "cpoet/database/inc/dialect/dialect_result.h"
#include "cpoet/database/inc/wrapper/sql_wrappers.h"
#include "cpoet/database/inc/wrapper/wrapper_executor.h"

#define DB_NAME "notebook"
#define DB_FILE_NAME "test.db"

void selectTest(WrapperExecutor *executor) {
    auto *wrapper = SqlWrappers::select("user")
            ->select("*")
            ->select("CREATE_TIME", "createTime")
            ->eq("name", "小东西")
            ->logicOr()
            ->eq("id", 2);
    auto *result = executor->findOne(wrapper);
    if (result != nullptr) {
        qDebug() << "查询结果：" << (*result);
        qDebug() << "时间：" << (*result)["CREATE_TIME"].toDateTime();
        delete result;
    }

    auto *list = executor->findList(wrapper);
    if (list != nullptr) {
        qDebug() << "查询结果：" << (*list);
        delete list;
    }
    delete wrapper;
}

void insetTest(WrapperExecutor *executor) {
    auto *wrapper = SqlWrappers::insert("user")
            ->set("name", "cpoet")
            ->set("version", 1)
            ->set("password", "123456")
            ->set("create_time", QDateTime::currentDateTime())
            ->set("update_time", QDateTime::currentDateTime());
    qDebug() << "受影响的条数：" << executor->execute(wrapper);
    delete wrapper;
}

void updateTest(WrapperExecutor *executor) {
    auto *wrapper = SqlWrappers::update("user")
            ->set("name", "小东西")
            ->eq("id", 1);
    qDebug() << "受影响的条数：" << executor->execute(wrapper);
    delete wrapper;
}

void updateTestCase1(WrapperExecutor *executor) {
    auto *wrapper = SqlWrappers::update("user")
            ->set("version", "version + 1", true)
            ->eq("id", 1);
    qDebug() << "更新：" << executor->execute(wrapper);
    delete wrapper;
}

void deleteTest(WrapperExecutor *executor) {
    auto *wrapper = SqlWrappers::deleted("user")
            ->eq("id", 2);
    qDebug() << "删除成功：" << executor->execute(wrapper);
    delete wrapper;
}

int main(int argc, char *argv[]) {
    QString dbPath = QFileInfo(__FILE__).absolutePath() + "/" + DB_FILE_NAME;
    DbContextHolder::setDefaultDbName(DB_NAME);
    QSqlDatabase db = QSqlDatabase::addDatabase("QSQLITE", DB_NAME);
    db.setDatabaseName(dbPath);
    auto *executor = new WrapperExecutor();
    selectTest(executor);
//    insetTest(executor);
//    updateTest(executor);
//    updateTestCase1(executor);
//    deleteTest(executor);
    delete executor;
    return 0;
}
