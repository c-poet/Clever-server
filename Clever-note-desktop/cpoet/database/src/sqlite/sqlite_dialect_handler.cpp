// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/2

#include "cpoet/core/inc/log.h"
#include "inc/sqlite/sqlite_dialect_func.h"
#include "inc/sqlite/sqlite_dialect_handler.h"

SqliteDialectHandler::SqliteDialectHandler(DialectContext *context, DialectResult *result) :
        _context(context),
        _result(result) {
}

void SqliteDialectHandler::exec() {
    SqlWrapperTypes wrapperType = this->_context->getWrapperType();
    switch (wrapperType) {
        case SqlWrapperTypes::QUERY:
            handleSelect(getTargetTypeWrapper<SelectWrapper>(), this->_result, this->_context);
            break;
        case SqlWrapperTypes::UPDATE:
            handleUpdate(getTargetTypeWrapper<UpdateWrapper>(), this->_result, this->_context);
            break;
        case SqlWrapperTypes::INSERT:
            handleInsert(getTargetTypeWrapper<InsertWrapper>(), this->_result, this->_context);
            break;
        case SqlWrapperTypes::DELETE:
            handleDelete(getTargetTypeWrapper<DeleteWrapper>(), this->_result, this->_context);
            break;
        default:
            this->_result->setFailed(true);
            this->_result->setMessage("尚未支持的Wrapper类型");
    }
}

template<class W>
W *SqliteDialectHandler::getTargetTypeWrapper() {
    return dynamic_cast<W *>(this->_context->getWrapper());
}
