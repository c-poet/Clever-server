// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/1

#include "inc/wrapper/update_wrapper.h"

const QString &UpdateWrapper::UpdateMapperChain::getColumn() const {
    return _column;
}

UpdateWrapper::UpdateMapperChain *UpdateWrapper::UpdateMapperChain::getNext() const {
    return _next;
}

const QVariant &UpdateWrapper::UpdateMapperChain::getVal() const {
    return _val;
}

bool UpdateWrapper::UpdateMapperChain::isCol() const {
    return _col;
}

UpdateWrapper::UpdateWrapper() :
        _start(nullptr),
        _end(nullptr) {}

UpdateWrapper::UpdateWrapper(const QString &table) :
        _table(table),
        _start(nullptr),
        _end(nullptr) {}

UpdateWrapper::~UpdateWrapper() {
    if (this->_start != nullptr) {
        auto *curChain = this->_start;
        UpdateMapperChain *swapChain;
        while (curChain != nullptr) {
            swapChain = curChain->_next;
            delete curChain;
            curChain = swapChain;
        }
    }
}

UpdateWrapper::UpdateMapperChain *
UpdateWrapper::newUpdateMapperChain(const QString &column, const QVariant &val, bool isCol) {
    auto *chain = new UpdateMapperChain();
    chain->_column = column;
    chain->_val = val;
    chain->_col = isCol;
    chain->_next = nullptr;
    return chain;
}

const QString &UpdateWrapper::getTable() const {
    return _table;
}

UpdateWrapper *UpdateWrapper::setTable(const QString &table) {
    _table = table;
    return this;
}

UpdateWrapper *UpdateWrapper::set(const QString &column, const QVariant &val) {
    return this->set(column, val, false);
}

UpdateWrapper *UpdateWrapper::set(const QString &column, const QVariant &val, bool isCol) {
    auto *chain = this->newUpdateMapperChain(column, val, isCol);
    if (this->_end == nullptr) {
        this->_start = chain;
        this->_end = chain;
    } else {
        this->_end->_next = chain;
        this->_end = chain;
    }
    return this;
}

UpdateWrapper *UpdateWrapper::reset(const QString &column, const QVariant &val) {
    return this->reset(column, val, false);
}

UpdateWrapper *UpdateWrapper::reset(const QString &column, const QVariant &val, bool isCol) {
    auto *chain = this->newUpdateMapperChain(column, val, isCol);
    if (this->_end == nullptr) {
        this->_start = chain;
        this->_end = chain;
    } else {
        if (this->_start->_column == column) {
            chain->_next = this->_start->_next;
            delete this->_start;
            this->_start = chain;
        } else {
            UpdateMapperChain *preNode = this->_start;
            UpdateMapperChain *curNode = this->_start->_next;
            while (curNode != nullptr && curNode->_column != column) {
                preNode = curNode;
                curNode = preNode->_next;
            }
            if (curNode == nullptr) {
                this->_end->_next = chain;
                this->_end = chain;
            } else {
                preNode->_next = chain;
                chain->_next = curNode->_next;
                delete curNode;
            }
        }
    }
    return this;
}

void UpdateWrapper::consume(const std::function<void(UpdateMapperChain *, UpdateMapperChain *)> &consumer) const {
    consumer(this->_start, this->_end);
}
