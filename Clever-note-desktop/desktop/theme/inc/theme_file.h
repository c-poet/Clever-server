// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#ifndef THEME_FILE_H
#define THEME_FILE_H

class ThemeItem;

/// @brief 文件类型
enum class FileType {
    CSS,
    SCSS
};

class ThemeFile {
    friend class ThemeItem;

private:
    // 文件名
    QString file;
    // 文件类型
    FileType fileType;
};

#endif
