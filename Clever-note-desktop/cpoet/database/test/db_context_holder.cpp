// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/3

#include <QThread>
#include "cpoet/core/inc/log.h"
#include "cpoet/database/inc/core/db_context_holder.h"

int main(int argc, char *argv[]) {
    qDebug() << "当前dbName:" << DbContextHolder::getDbName();
    DbContextHolder::setDbName("sqlite");
    qDebug() << "修改后的dbName:" << DbContextHolder::getDbName();
}
