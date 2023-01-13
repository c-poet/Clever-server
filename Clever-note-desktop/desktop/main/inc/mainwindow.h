// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include "desktop/richtext/inc/richtext.h"

class MainWindow : public QMainWindow
{
    Q_OBJECT

private:
    ///
    /// \brief 编辑器
    ///
    Editor* editor;

public:
    explicit MainWindow(QWidget *parent = nullptr);
    ~MainWindow() override;
};
#endif // MAINWINDOW_H
