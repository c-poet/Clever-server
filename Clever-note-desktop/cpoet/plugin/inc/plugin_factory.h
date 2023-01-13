// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#ifndef C_POET_PLUGIN_FACTORY_H
#define C_POET_PLUGIN_FACTORY_H

#include "plugin.h"

class PluginFactory {
public:
    /// @brief 加载插件
    /// @param library 动态库实例
    /// @return 插件实例
    static Plugin *load(QLibrary *library) noexcept(false);

    /// @brief 加载插件
    /// @param file 插件动态库入口文件
    /// @return 插件实例
    static Plugin *load(const QString &file) noexcept(false);
};

#endif
