// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include "inc/mainwindow.h"
#include <QToolBar>
#include <QSplitter>
#include <QListWidget>
#include <QTreeWidget>
#include "cpoet/core/inc/core.h"
#include "desktop/theme/inc/constant.h"
#include "desktop/theme/inc/theme.h"

MainWindow::MainWindow(QWidget *parent) : QMainWindow(parent) {
    // 隐藏默认的标题栏
//    this->setWindowFlags(Qt::FramelessWindowHint);
    
#ifdef Q_OS_MACOS
    // 如果是Macos的情况下，将注册菜单栏
#endif

    auto *theme = new Theme();
    theme->load(THEME_DEFAULT_NAME);
    this->setWindowTitle(tr("Clever"));
    this->setMinimumWidth(820);
    this->setMinimumHeight(600);

    // 左
    auto *shapeToolBar = new QToolBar(this);
    shapeToolBar->setMovable(false);
    // shapeToolBar->addAction("头像");
    // shapeToolBar->addSeparator();
    shapeToolBar->addAction(tr("Todo"));
    shapeToolBar->addAction(tr("Notebook"));
    shapeToolBar->addAction(tr("Calendar"));
    shapeToolBar->addAction(tr("Recycle"));
    shapeToolBar->addAction(tr("Setting"));
    this->addToolBar(Qt::LeftToolBarArea, shapeToolBar);

    // 右
    auto *splitter = new QSplitter(Qt::Horizontal, this);
    // 目录导航
    auto *treeWidget = new QTreeWidget(this);
    treeWidget->setHeaderLabel("2022年笔记");
    // 个人记录
    auto *item1 = new QTreeWidgetItem(treeWidget);
    item1->setText(0, "个人记录");

    auto *item11 = new QTreeWidgetItem(item1);
    item11->setText(0, "2022年学习路线.note");
    item11->setSelected(true);

    // 工作笔记
    auto *item2 = new QTreeWidgetItem(treeWidget);
    item2->setText(0, "工作笔记");
    // 学习笔记
    auto *item3 = new QTreeWidgetItem(treeWidget);
    item3->setText(0, "学习笔记");
    splitter->addWidget(treeWidget);

//    auto *listWidget = new QListWidget(this);
//    listWidget->setMinimumWidth(10);
//    QString listWidgetStyle = (*theme)["main-window-right-list"];
//    qInfo() << listWidgetStyle;
//    if (!listWidgetStyle.isEmpty()) {
//        listWidget->setStyleSheet(listWidgetStyle);
//    }
//    auto *item = new QListWidgetItem("个人记录");
//    listWidget->addItem(item);
//    listWidget->addItem("工作笔记");
//    listWidget->addItem("学习笔记");
//    splitter->addWidget(listWidget);

    // 编辑器
    this->editor = new RichTextEditor(this);
    splitter->addWidget(this->editor);
    splitter->setStretchFactor(0, 3);
    splitter->setStretchFactor(1, 7);
    this->setCentralWidget(splitter);

    QString style = "#test{color:red;}";
    auto document = this->editor->document();
    document->setDefaultStyleSheet(style);
    this->editor->insertHtml("<p id=\"test\">Test</p>");
    qInfo() << this->editor->toHtml();

    delete theme;
}

MainWindow::~MainWindow() = default;
