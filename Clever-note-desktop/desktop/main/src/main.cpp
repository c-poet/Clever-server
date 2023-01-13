// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include <QApplication>
#include "inc/mainwindow.h"

/// @brief YunZhi-Note-Desktop (云知笔记桌面端)
/// @author CPoet
/// @date 2022-10-10
int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindow w;
    w.show();
    return a.exec();
}
