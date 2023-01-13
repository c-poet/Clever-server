cmake_minimum_required(VERSION 3.5)

# 语言支持C++
project(Clever-note-desktop VERSION 1.0.0 LANGUAGES CXX)

# 开启单元测试
enable_testing()

set(CMAKE_INCLUDE_CURRENT_DIR ON)

set(CMAKE_AUTOUIC ON)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)

# 指定C++版本
set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# MSVC编译环境配置
if (MSVC)
    # 导出所有符号
    set(CMAKE_WINDOWS_EXPORT_ALL_SYMBOLS ON)
    # 编码问题
    set(CMAKE_CXX_FLAGS /utf-8)
endif ()

# 指定输出目录
set(NOTE_OUTPUT_PATH ${PROJECT_BINARY_DIR}/bin)
set(EXECUTABLE_OUTPUT_PATH ${NOTE_OUTPUT_PATH})
set(LIBRARY_OUTPUT_PATH ${NOTE_OUTPUT_PATH})
set(THEME_OUTPUT_PATH ${NOTE_OUTPUT_PATH}/theme)
set(PLUGIN_OUTPUT_PATH ${NOTE_OUTPUT_PATH}/plugin)
set(LANG_OUTPUT_PATH ${NOTE_OUTPUT_PATH}/lang)

# MSVC does not support rpath
if (MSVC)
    set(EXT_LIBRARY_OUTPUT_PATH ${NOTE_OUTPUT_PATH})
else ()
    set(EXT_LIBRARY_OUTPUT_PATH ${NOTE_OUTPUT_PATH}/lib)
endif ()

# 常用目录
set(NOTE_C_POET_PATH ${PROJECT_SOURCE_DIR}/cpoet)
set(NOTE_DESKTOP_PATH ${PROJECT_SOURCE_DIR}/desktop)
set(NOTE_THEME_PATH ${PROJECT_SOURCE_DIR}/theme)
set(NOTE_PLUGIN_PATH ${PROJECT_SOURCE_DIR}/plugin)
set(NOTE_LIB_PATH ${PROJECT_SOURCE_DIR}/lib)
set(NOTE_LANG_PATH ${PROJECT_SOURCE_DIR}/lang)

# 国际化配置，支持语言文件
# 各执行模块使用 qt5_create_translation 引用
set(TS_FILE_LIST
        ${NOTE_LANG_PATH}/zh_CN.ts
        ${NOTE_LANG_PATH}/zh_TW.ts)


# 搜索QT组件及包
find_package(QT NAMES Qt6 Qt5 COMPONENTS Widgets LinguistTools Sql REQUIRED)
find_package(Qt${QT_VERSION_MAJOR} COMPONENTS Widgets LinguistTools Sql REQUIRED)

# 递归扫描所有头文件
function(list_include_file resultVar rootPath)
    file(GLOB files "${rootPath}/*")
    foreach (file ${files})
        if (IS_DIRECTORY ${file})
            list_include_file(result ${file})
            foreach (resultItem ${result})
                list(APPEND tempResult ${resultItem})
            endforeach ()
        elseif (${file} MATCHES "^.+\\.h|(hpp)$")
            list(APPEND tempResult ${file})
        endif ()
    endforeach ()
    # 去除重复的头文件
    list(REMOVE_DUPLICATES tempResult)
    set(${resultVar} ${tempResult} PARENT_SCOPE)
endfunction()

# 递归扫描所有源文件
function(list_source_file resultVar rootPath)
    file(GLOB files "${rootPath}/*")
    foreach (file ${files})
        if (IS_DIRECTORY ${file})
            list_source_file(result ${file})
            foreach (resultItem ${result})
                list(APPEND tempResult ${resultItem})
            endforeach ()
        elseif (${file} MATCHES "^.+\\.cpp$")
            list(APPEND tempResult ${file})
        endif ()
    endforeach ()
    list(REMOVE_DUPLICATES tempResult)
    set(${resultVar} ${tempResult} PARENT_SCOPE)
endfunction()

# 拷贝外部库函数（静态库及动态库、符号信息文件后缀同静态库）
function(copy_all_ext_lib rootPath outPath)
    set(extRegex "(so)|a")
    if (NOT CMAKE_SYSTEM_NAME MATCHES Linux)
        set(extRegex "(dll)|(lib)")
    endif ()
    file(GLOB files "${rootPath}/*")
    foreach (file ${files})
        if (IS_DIRECTORY ${file})
            # 如果是目录的话则进行递归
            copy_all_ext_lib(${file} ${outPath})
        else ()
            if (${file} MATCHES "^.+\\.${extRegex}$")
                # 判断动态库是否区分64位或32位
                if (${file} MATCHES "^.+/x64/.+$")
                    if (CMAKE_SIZEOF_VOID_P EQUAL 8 OR CMAKE_CL_64)
                        file(COPY ${file} DESTINATION ${outPath})
                    endif ()
                elseif (${file} MATCHES "^.+/x86/.+$")
                    if (CMAKE_SIZEOF_VOID_P EQUAL 4 OR NOT CMAKE_CL_64)
                        file(COPY ${file} DESTINATION ${outPath})
                    endif ()
                else ()
                    file(COPY ${file} DESTINATION ${outPath})
                endif ()
            endif ()
        endif ()
    endforeach ()
endfunction()

# 调用函数拷贝外部库
copy_all_ext_lib(${NOTE_LIB_PATH} ${EXT_LIBRARY_OUTPUT_PATH})
# 指定链接库目录
link_directories(${EXT_LIBRARY_OUTPUT_PATH})

# 拷贝资源文件　
file(GLOB targetTheme ${NOTE_THEME_PATH}/*)
file(COPY ${targetTheme} DESTINATION ${THEME_OUTPUT_PATH})

# 添加项目头文件搜索目录，方便后续按模块引用
include_directories(${PROJECT_SOURCE_DIR})

# 添加子目录
add_subdirectory(cpoet)
add_subdirectory(desktop)
add_subdirectory(plugin)
add_subdirectory(lib)
