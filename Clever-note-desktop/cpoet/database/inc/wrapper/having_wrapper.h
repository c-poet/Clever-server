// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/5

#ifndef C_POET_HAVING_WRAPPER_H
#define C_POET_HAVING_WRAPPER_H

#include "sql_wrapper.h"

/// @brief having子句，在Group之后使用
template<class W>
class HavingWrapper : virtual public SqlWrapper {

};

#endif
