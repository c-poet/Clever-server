// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include "inc/richtext.h"
#include <QAction>

RichTextEditor::RichTextEditor(QWidget *parent) : Editor(parent) {
    QString html = "<html><head><style>#test{color:red;}<style></head></html>";
    this->setHtml(html);
}

RichTextEditor::~RichTextEditor() = default;
