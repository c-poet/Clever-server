# 删除生成动态库名称前缀
set(CMAKE_SHARED_LIBRARY_PREFIX)

# 指定插件输出目录
set(LIBRARY_OUTPUT_PATH ${PLUGIN_OUTPUT_PATH})

add_subdirectory(github)
