// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include "inc/plugin_factory.h"
#include "cpoet/core/inc/log.h"
#include "cpoet/core/inc/exception.h"

Plugin *PluginFactory::load(QLibrary *library) noexcept(false) {
    auto *plugin = new Plugin(library);
    try {
        plugin->reload();
    } catch (Exception &e) {
        delete plugin;
        throw e;
    }
    return plugin;
}

Plugin *PluginFactory::load(const QString &file) noexcept(false) {
    return load(new QLibrary(file));
}

