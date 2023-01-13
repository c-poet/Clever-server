// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include "inc/css/css_handle_func.h"
#include <QDebug>

QString getCurrentWord(int i, const QString &str) {
    return getCurrentWordIndex(i, str);
}

QString getCurrentWordIndex(int &i, const QString &str) {
    QString result;
    int len = str.size();
    while (i < len && str[i] == ' ') ++i;
    while (i < len && str[i] != ' ') {
        result.append(str[i]);
        ++i;
    }
    return result;
}

bool isMathCurrentWord(int i, const QString &str, const QString &word) {
    return isMathCurrentWordIndex(i, str, word);
}

bool isMathCurrentWordIndex(int &i, const QString &str, const QString &word) {
    return word == getCurrentWordIndex(i, str);
}
