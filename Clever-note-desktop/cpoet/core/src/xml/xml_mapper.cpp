// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include "inc/xml/xml_mapper.h"


XmlMapper::XmlMapper(QMap<QString, QString> *mapper) {
    this->mapper = mapper;
}

XmlMapper::~XmlMapper() {
    delete this->mapper;
}

const QMap<QString, QString> *XmlMapper::getAllMapper() {
    return this->mapper;
}

bool XmlMapper::isEmpty() {
    return this->mapper->isEmpty();
}

QString XmlMapper::get(const QString &key) {
    return (*this->mapper)[key];
}

QString XmlMapper::get(const QString &key, const QString &defaultVal) {
    QString val = get(key);
    return val.isEmpty() ? defaultVal : val;
}

long XmlMapper::getAsLong(const QString &key) {
    return getAsLong(key, 0);
}

long XmlMapper::getAsLong(const QString &key, long defaultVal) {
    QString val = get(key);
    if (val.isEmpty()) {
        return defaultVal;
    }
    bool ok = false;
    long toVal = val.toLong(&ok);
    return ok ? toVal : defaultVal;
}

int XmlMapper::getAsInt(const QString &key) {
    return getAsInt(key, 0);
}

int XmlMapper::getAsInt(const QString &key, int defaultVal) {
    QString val = get(key);
    if (val.isEmpty()) {
        return defaultVal;
    }
    bool ok = false;
    int toVal = val.toInt(&ok);
    return ok ? toVal : defaultVal;
}

float XmlMapper::getAsFloat(const QString &key) {
    return getAsFloat(key, 0);
}

float XmlMapper::getAsFloat(const QString &key, float defaultVal) {
    QString val = get(key);
    if (val.isEmpty()) {
        return defaultVal;
    }
    bool ok = false;
    float toVal = val.toFloat(&ok);
    return ok ? toVal : defaultVal;
}

double XmlMapper::getAsDouble(const QString &key) {
    return getAsDouble(key, 0);
}

double XmlMapper::getAsDouble(const QString &key, double defaultVal) {
    QString val = get(key);
    if (val.isEmpty()) {
        return defaultVal;
    }
    bool ok = false;
    double toVal = val.toDouble(&ok);
    return ok ? toVal : defaultVal;
}

bool XmlMapper::getAsBool(const QString &key) {
    return getAsBool(key, false);
}

bool XmlMapper::getAsBool(const QString &key, bool defaultVal) {
    QString val = get(key);
    if (val.isEmpty()) {
        return defaultVal;
    }
    return val == "1" || val.toLower() == "true";
}

QMap<QString, QString> *XmlMapper::getAsMap(const QString &key) {
    auto *map = new QMap<QString, QString>();
    QList<QString> keys = this->mapper->keys();
    for (const auto &k: keys) {
        if (k.indexOf(key) == 0) {
            map->insert(k, (*this->mapper)[k]);
        }
    }
    return map;
}

QList<QString> *XmlMapper::getAsList(const QString &key) {
    auto *list = new QList<QString>();
    QList<QString> keys = this->mapper->keys();
    for (const auto &k: keys) {
        if (k.indexOf(key) == 0) {
            list->append((*this->mapper)[k]);
        }
    }
    return list;
}
