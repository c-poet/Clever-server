// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#ifndef  EDITOR_H
#define EDITOR_H

#include <QTextEdit>

/// @brief 编辑器类型
enum class EditorType {
    // 富文本编辑器
    RICH_TEXT,
    // Markdown支持
    MARKDOWN
};

class Editor : public QTextEdit {
Q_OBJECT
public:
    explicit Editor(QWidget *parent = nullptr);

    ~Editor() override;

    /// @brief 获取编辑器的类型
    /// @return 编辑器类型
    virtual EditorType editorType() = 0;
};

#endif // ! EDITOR_H
