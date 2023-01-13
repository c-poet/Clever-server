// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include "inc/exception.h"

Exception::Exception() noexcept = default;

Exception::Exception(const QString &message) noexcept {
    this->message = message;
}

Exception::Exception(const Exception &other) noexcept: exception(other) {
    this->message = other.message;
}

QString Exception::getMessage() {
    return this->message;
}

void Exception::setMessage(const QString &msg) {
    this->message = msg;
}
