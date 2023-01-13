// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include "cpoet/core/inc/core.h"
#include <QDir>
#include <QLibrary>
#include <QList>

#define PLUGIN_PATH "plugin"
#define PLUGIN_INIT_FUNC "init"
#define PLUGIN_START_FUNC "start"

typedef int (*Handle)(int argc, char *args[]);

/// @brief 动态加载库测试
/// @author CPoet
int main(int argc, char *argv[]) {
    auto *libraries = new QList<QLibrary *>();
    auto *dir = new QDir(getApplicationFilePath(PLUGIN_PATH));
    if (dir->exists()) {
        qDebug() << "扫描插件目录：" << dir->path();
        QStringList list = dir->entryList(QStringList("*.so"), QDir::Filter::Files);
        if (!list.isEmpty()) {
            qDebug() << "遍历插件并调用约定方法，插件数量：" << list.size();
            for (const auto &item: list) {
                QString itemPath = getFitFilePath(dir->path(), item);
                qDebug() << "加载插件：" << itemPath;
                auto *lib = new QLibrary(itemPath);
                libraries->append(lib);
                if (lib->load()) {
                    qDebug() << "加载插件：" << item << "成功";
                    QFunctionPointer initFunc = lib->resolve(PLUGIN_INIT_FUNC);
                    if (initFunc) {
                        qDebug() << "调用init方法返回" << ((Handle) initFunc)(10, nullptr);
                    }
                    QFunctionPointer startFunc = lib->resolve(PLUGIN_START_FUNC);
                    if (startFunc) {
                        qDebug() << "调用start方法返回" << ((Handle) startFunc)(1000, nullptr);
                    }
                }
            }
        }
    } else {
        qDebug() << "插件目录不存在";
    }

    if (!libraries->isEmpty()) {
        int len = libraries->size();
        for (int i = 0; i < len; ++i) {
            QLibrary *lib = (*libraries)[i];
            if (lib->isLoaded()) {
                qDebug() << "关闭插件：" << lib->fileName() << ":" << lib->unload();
            }
            delete lib;
        }
    }
    delete dir;
    delete libraries;
    return 0;
}
