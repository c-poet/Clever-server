// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include "inc/css/css.h"
#include "inc/css/css_parse.h"
#include "cpoet/core/inc/core.h"


QMap<QString, QString> *CssParser::parse4path(const QString &filePath) {
    return parseCss4path(filePath, getAbsolutePath(filePath));
}

QMap<QString, QString> *CssParser::parse4path(const QString &basePath, const QString &file) {
    return parseCss4path(getFitFilePath(basePath, file), basePath);
}

QMap<QString, QString> *CssParser::parse(const QString &css) {
    return css.isEmpty() ? nullptr : parseCss(css);
}

QMap<QString, QString> *CssParser::parse(const QString &css, const QString &basePath) {
    return css.isEmpty() ? nullptr : parseCss(css, basePath);
}
