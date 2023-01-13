// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include "inc/core.h"
#include "inc/constant.h"
#include <QCoreApplication>

QString getApplicationDirPath() {
    return QCoreApplication::applicationDirPath();
}

QString getApplicationFilePath(const QString &file) {
    return getFitFilePath(getApplicationDirPath(), file);
}

QString getFitFilePath(const QString &filePath) {
    QString path = filePath;
    return path.replace(PATH_SEPARATOR_WIN, PATH_SEPARATOR);
}

QString getFitFilePath(const QString &basePath, const QString &fileName) {
    QString file = getFitFilePath(fileName);
    if (basePath.isEmpty()) {
        return file;
    }
    QString path = getFitFilePath(basePath);
    if (path.endsWith(PATH_SEPARATOR_CHAR)) {
        return path + (file.startsWith(PATH_SEPARATOR_CHAR) ? file.mid(1) : file);
    }
    return path + (file.startsWith(PATH_SEPARATOR_CHAR) ? file : PATH_SEPARATOR + file);
}
