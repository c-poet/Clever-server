// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/1

#include "cpoet/core/inc/log.h"
#include "cpoet/database/inc/wrapper/sql_wrappers.h"
#include "cpoet/database/inc/dialect/dialect_factory.h"

/// @brief 测试SelectWrapper
int main(int argc, char *argv[]) {
    auto *wrapper = SqlWrappers::select("user", "U")
            ->regionLeft()
            ->eq("name", "cpoet")
            ->logicOr()
            ->eq("email", "llzero54@foxmail.com")
            ->regionRight()
            ->eq("password", "123456")
            ->isNull("is_deleted")
            ->asc("id")
            ->desc({"name", "create_time"});
    auto *result = DialectFactory::dialectSilence("sqlite", wrapper);
    if (result != nullptr) {
        qDebug() << "翻译结果：" << result->getSql();
        qDebug() << "参数：" << (*result->getParams());
        delete result;
    }
    delete wrapper;
}
