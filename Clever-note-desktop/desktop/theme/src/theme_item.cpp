// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include "inc/theme_item.h"
#include "cpoet/core/inc/log.h"
#include "cpoet/theme/inc/css/css.h"

ThemeItem::ThemeItem() {
    this->loaded = false;
    this->files = new QList<ThemeFile *>();
    this->sheet = new QMap<QString, QString>;
}

ThemeItem::~ThemeItem() {
    if (!this->files->isEmpty()) {
        for (const auto &file : *this->files) {
            delete file;
        }
    }
    delete this->files;
    delete this->sheet;
}

bool ThemeItem::isLoaded() const {
    return this->loaded;
}

bool ThemeItem::load() {
    if (this->loaded) {
        return false;
    }
    for (const auto &file : *this->files) {
        if (file->fileType == FileType::SCSS) {
            this->loadByScss(file);
        } else {
            this->loadByCss(file);
        }
    }
    this->loaded = true;
    return true;
}

bool ThemeItem::reload() {
    this->loaded = false;
    return this->load();
}

void ThemeItem::loadByCss(ThemeFile *file) {
    auto *result = CssParser::parse4path(this->basePath, file->file);
}

void ThemeItem::loadByScss(ThemeFile *file) {
    qWarning() << "Scss类型的样式文件支持正在开发中...";
}
