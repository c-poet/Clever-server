// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#ifndef CORE_H
#define CORE_H

#include "log.h"
#include <QString>

/// @brief 获取当前执行程序所在路径
/// @return 执行程序所在根路径
QString getApplicationDirPath();

/// @brief 获取当前程序文件路径
/// @param file 文件名称
/// @return 程序文件所在路径
QString getApplicationFilePath(const QString &file);

/// @brief 获取适配QT的文件路径，将"\\"替换为"/"
/// @param filePath 文件路径
/// @return 替换结果
QString getFitFilePath(const QString &filePath);

/// @brief 拼接适配QT的文件路径
/// @param basePath 文件所在路径
/// @param fileName 文件名
/// @return 文件完整路径
QString getFitFilePath(const QString &basePath, const QString &fileName);

#endif
