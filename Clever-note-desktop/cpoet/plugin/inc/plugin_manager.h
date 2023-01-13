// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#ifndef C_POET_PLUGIN_MANAGER_H
#define C_POET_PLUGIN_MANAGER_H

#include "plugin.h"
#include <QMap>

/// @brief 插件管理
class PluginManager {
private:
    QMap<QString, Plugin *> plugins;
public:
  
};

#endif
