#ifndef PLUGIN_GITHUB_H
#define PLUGIN_GITHUB_H

#include "core.h"
#include "cpoet/plugin/inc/plugin_meta.h"

extern "C" {
/// @brief 入口函数，获取插件信息
/// @return 插件信息
PUBLIC_API PluginInfo *pluginMain();
}
#endif
