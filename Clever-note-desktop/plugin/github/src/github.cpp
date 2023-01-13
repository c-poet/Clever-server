#include "inc/github.h"

PluginInfo *pluginMain() {
    auto *pluginInfo = new PluginInfo();
    pluginInfo->id = "plugin-github";
    pluginInfo->name = "plugin-github";
    pluginInfo->author = "CPoet";
    pluginInfo->description = "Github同步插件";
    return pluginInfo;
}

