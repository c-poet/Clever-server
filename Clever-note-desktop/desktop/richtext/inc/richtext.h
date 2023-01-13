// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#ifndef RICH_TEXT_H
#define RICH_TEXT_H

#include "desktop/editor/inc/editor.h"

class RichTextEditor : public Editor {
public:
    explicit RichTextEditor(QWidget *parent = nullptr);

    ~RichTextEditor() override;

    inline EditorType editorType() override {
        return EditorType::RICH_TEXT;
    };

};

#endif
