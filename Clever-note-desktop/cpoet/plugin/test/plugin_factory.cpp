// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include <cpoet/core/inc/core.h>
#include <cpoet/core/inc/exception.h>
#include "cpoet/plugin/inc/plugin_factory.h"

/// @brief 插件加载测试
int main(int argc, char *argv[]) {
#ifdef Q_OS_WINDOWS
    QString dllFile = getApplicationFilePath("plugin/plugin-github.dll");
#else
    QString dllFile = getApplicationFilePath("plugin/plugin-github.so");
#endif
    try {
        auto plugin = PluginFactory::load(dllFile);
        qDebug() << "插件加载成功";
        delete plugin;
    } catch (Exception &e) {
        qDebug() << "插件加载失败：" << e.getMessage();
    }
}
