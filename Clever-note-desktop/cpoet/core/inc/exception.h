// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#ifndef C_POET_EXCEPTION_H
#define C_POET_EXCEPTION_H

#include <QString>
#include <QException>

/// @brief 全局统一异常父级
class Exception : public std::exception {
private:
    // 异常信息
    QString message;

public:
    /// @brief 创建异常，异常信息为空
    explicit Exception() noexcept;

    /// @brief 创建异常
    /// @param message 异常信息
    explicit Exception(const QString &message) noexcept;

    /// @brief 异常信息拷贝
    /// @param other 异常信息
    Exception(const Exception &other) noexcept;

    /// @brief 获取异常信息
    /// @return 异常信息
    QString getMessage();

    /// @brief 设置异常信息
    /// @param message 异常信息
    void setMessage(const QString &msg);
};

#endif
