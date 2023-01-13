// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include "cpoet/core/inc/core.h"
#include "cpoet/theme/inc/css/css.h"
#include "inc/constant.h"
#include "inc/theme.h"

Theme::Theme() {
    this->sheetMapper = new QMap<QString, QString>();
}

Theme::~Theme() {
    delete this->sheetMapper;
}

QString &Theme::find(const QString &key) {
    return (*this)[key];
}

QString Theme::find(const QString &key, const QString &defaultVal) {
    return (*this->sheetMapper).value(key, defaultVal);
}

void Theme::add(const QString &key, const QString &value) {
    (*this->sheetMapper)[key] = value;
}

void Theme::load(const QString &themeName) {
    QString path = getApplicationFilePath(getFitFilePath("", themeName));
    return this->load4path(path);
}

void Theme::load4path(const QString &basePath) {
    return this->load4file(basePath, "");
}

void Theme::load4file(const QString &filePath) {
    QMap<QString, QString> *result = CssParser::parse4path(filePath);
    if (result != nullptr && !result->isEmpty()) {
        this->copy(result);
    }
    delete result;
}

void Theme::load4file(const QString &basePath, const QString &fileName) {
    QMap<QString, QString> *result = CssParser::parse4path(basePath, fileName);
    if (result != nullptr && !result->isEmpty()) {
        this->copy(result);
    }
    delete result;
}

void Theme::del(const QString &key) {
    (*this->sheetMapper).remove(key);
}

void Theme::delAll() {
    (*this->sheetMapper).clear();
}

void Theme::copy(const QMap<QString, QString> *sheetMap) {
    QList<QString> keys = sheetMap->keys();
    int i = keys.size();
    while (--i >= 0) {
        (*this->sheetMapper)[keys[i]].append((*sheetMap)[keys[i]]);
    }
}

void Theme::copy(const Theme *other) {
    if (other != nullptr && other->sheetMapper != nullptr && !other->sheetMapper->isEmpty()) {
        this->copy(other->sheetMapper);
    }
}
