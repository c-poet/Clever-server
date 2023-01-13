// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/1

#include "inc/dialect/dialect_context.h"

SqlWrapperTypes DialectContext::getWrapperType() const {
    return _wrapperType;
}

void DialectContext::setWrapperType(SqlWrapperTypes wrapperType) {
    _wrapperType = wrapperType;
}

SqlWrapper *DialectContext::getWrapper() const {
    return _wrapper;
}

void DialectContext::setWrapper(SqlWrapper *wrapper) {
    _wrapper = wrapper;
}
