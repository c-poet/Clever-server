// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/7

#include <QString>
#include <QFileInfo>
#include <QFile>
#include <QDir>
#include <QDateTime>
#include "cpoet/core/inc/log.h"
#include "contrib/minizip/zip.h"

void write2zip(zipFile file, const QFileInfo &fileInfo, const QString &dirName = QString()) {
    if (fileInfo.isFile()) {
        zip_fileinfo fInfo;
        zipOpenNewFileInZip(file, (dirName + "/" + fileInfo.fileName()).toStdString().c_str(), &fInfo, "",
                            0, "", 0, "", Z_DEFLATED, Z_BEST_COMPRESSION);
        QFile f(fileInfo.filePath());
        if (f.open(QIODevice::ReadOnly)) {
            QByteArray byteArray = f.readAll();
            zipWriteInFileInZip(file, byteArray.toStdString().c_str(), byteArray.size());
        } else {
            qDebug() << "读取文件失败：" << fileInfo.fileName();
        }
        zipCloseFileInZip(file);
    } else if (fileInfo.isDir()) {
        QDir dir = fileInfo.dir();
        QFileInfoList fileInfoList = dir.entryInfoList();
        for (const auto &item : fileInfoList) {
            qDebug() << item.fileName();
            write2zip(file, item, fileInfo.fileName());
        }
    } else {
        qDebug() << "错误的文件类型:" << fileInfo.filePath();
    }
}

int main(int argc, char *argv[]) {
    // 输出目录
    QString path = QFileInfo(__FILE__).absolutePath() + "/../../../../zipOut.zip";
    QFileInfo fileInfo(QFileInfo(__FILE__).absolutePath() + "/../../../");
    auto *file = zipOpen(path.toStdString().c_str(), APPEND_STATUS_CREATE);
    if (file != nullptr) {
        write2zip(file, fileInfo);
        zipClose(file, "llzero54@foxmail.com");
    } else {
        qDebug() << "文件打开失败";
    }
    return 0;
}

