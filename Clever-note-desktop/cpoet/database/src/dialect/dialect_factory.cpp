// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/1

#include "cpoet/core/inc/log.h"
#include "cpoet/core/inc/exception.h"
#include "inc/sqlite/sqlite_dialect.h"
#include "inc/dialect/dialect_names.h"
#include "inc/dialect/dialect_factory.h"

/// @brief 注册默认的数据库方言
void registerDefaultDialects(DialectFactory *factory) {
    factory->registerDialect(SQL_DIALECT_SQLITE, new SqliteDialect());
}

DialectFactory DialectFactory::INSTANCE; /* NOLINT */

DialectFactory::DialectFactory() : _dialects(nullptr) {
    qDebug() << "数据库方言工厂实例化";
    registerDefaultDialects(this);
}

DialectFactory::~DialectFactory() {
    qDebug() << "数据库方言工厂释放";
    if (this->_dialects != nullptr && !this->_dialects->isEmpty()) {
        QList<Dialect *> dialectList = this->_dialects->values();
        for (const auto &dialect: dialectList) {
            delete dialect;
        }
    }
    delete this->_dialects;
}

DialectFactory &DialectFactory::getInstance() {
    return DialectFactory::INSTANCE;
}

Dialect *DialectFactory::getDialect(const QString &dialectName) {
    return (*this->_dialects)[dialectName];
}

void DialectFactory::registerDialect(const QString &dialectName, Dialect *dialect) {
    if (this->_dialects == nullptr) {
        this->_dialects = new QMap<QString, Dialect *>();
    }
    qDebug() << "注册名称为：" << dialectName << "的数据库方言";
    (*this->_dialects)[dialectName] = dialect;
}

bool DialectFactory::removeDialect(const QString &dialectName) {
    auto *dialect = (*this->_dialects).take(dialectName);
    if (dialect == nullptr) {
        return false;
    }
    delete dialect;
    return true;
}

Dialect *DialectFactory::get(const QString &dialectName) {
    return DialectFactory::getInstance().getDialect(dialectName);
}

DialectResult *DialectFactory::dialect(const QString &dialectName, SqlWrapper *wrapper) noexcept(false) {
    Dialect *targetDialect = DialectFactory::get(dialectName);
    if (targetDialect == nullptr) {
        throw Exception("名称为:" + dialectName + "的数据库方言不存在");
    }
    auto *context = new DialectContext();
    context->setWrapper(wrapper);
    context->setWrapperType(wrapper->type());
    auto *result = targetDialect->unwrap(context);
    delete context;
    return result;
}

DialectResult *DialectFactory::dialectSilence(const QString &dialectName, SqlWrapper *wrapper) {
    DialectResult *result = nullptr;
    try {
        result = DialectFactory::dialect(dialectName, wrapper);
    } catch (Exception &e) {
        qWarning() << "数据库方言翻译失败：" << e.getMessage();
    }
    return result;
}
