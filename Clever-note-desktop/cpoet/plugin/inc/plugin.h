// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#ifndef C_POET_PLUGIN_H
#define C_POET_PLUGIN_H

#include <QLibrary>
#include "plugin_meta.h"

class Plugin {
private:
    QString libFile;
    QLibrary *library;
public:
    explicit Plugin(const QString &file);

    explicit Plugin(QLibrary *lib);

    virtual ~Plugin();

    void load() noexcept(false);

    void reload() noexcept(false);
};

#endif
