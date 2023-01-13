// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/1

#ifndef C_POET_DIALECT_CONTEXT_H
#define C_POET_DIALECT_CONTEXT_H

#include "../wrapper/sql_wrapper.h"

/// @brief 方言翻译上下文
class DialectContext {
private:
    SqlWrapperTypes _wrapperType;
    SqlWrapper *_wrapper;

public:
    SqlWrapperTypes getWrapperType() const;

    void setWrapperType(SqlWrapperTypes wrapperType);

    SqlWrapper *getWrapper() const;

    void setWrapper(SqlWrapper *wrapper);
};

#endif
