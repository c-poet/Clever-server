// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/1

#include "inc/wrapper/select_wrapper.h"

const QString &SelectWrapper::ColumnChain::getName() const {
    return _name;
}

SelectWrapper::ColumnChain *SelectWrapper::ColumnChain::setName(const QString &name) {
    _name = name;
    return this;
}

const QString &SelectWrapper::ColumnChain::getAlias() const {
    return _alias;
}

SelectWrapper::ColumnChain *SelectWrapper::ColumnChain::setAlias(const QString &alias) {
    _alias = alias;
    return this;
}

SelectWrapper::ColumnChain *SelectWrapper::ColumnChain::getNext() const {
    return _next;
}

SelectWrapper::SelectWrapper() :
        _columnStart(nullptr), _columnEnd(nullptr) {
}

SelectWrapper::SelectWrapper(const QString &table) :
        _table(table),
        _columnStart(nullptr),
        _columnEnd(nullptr) {
}

SelectWrapper::SelectWrapper(const QString &table, const QString &alias) :
        _table(table),
        _alias(alias),
        _columnStart(nullptr),
        _columnEnd(nullptr) {

}

SelectWrapper::~SelectWrapper() {
    if (this->_columnStart != nullptr) {
        auto *curNode = this->_columnStart;
        ColumnChain *nextNode = nullptr;
        while (curNode != nullptr) {
            nextNode = curNode->_next;
            delete curNode;
            curNode = nextNode;
        }
    }
}

const QString &SelectWrapper::getTable() const {
    return _table;
}

SelectWrapper *SelectWrapper::setTable(const QString &table) {
    _table = table;
    return this;
}

const QString &SelectWrapper::getAlias() const {
    return _alias;
}

SelectWrapper *SelectWrapper::setAlias(const QString &alias) {
    _alias = alias;
    return this;
}

SelectWrapper *SelectWrapper::addColumn(const QString &column, const QString &alias) {
    auto *newChain = (new ColumnChain())
            ->setName(column)
            ->setAlias(alias);
    if (this->_columnEnd == nullptr) {
        this->_columnStart = newChain;
        this->_columnEnd = newChain;
    } else {
        this->_columnEnd->_next = newChain;
        this->_columnEnd = newChain;
    }
    return this;
}

SelectWrapper *SelectWrapper::select(const QString &column) {
    return this->addColumn(column);
}

SelectWrapper *SelectWrapper::select(const QList<QString> &columns) {
    if (!columns.isEmpty()) {
        for (const auto &column: columns) {
            this->select(column);
        }
    }
    return this;
}

SelectWrapper *SelectWrapper::select(const QString &column, const QString &alias) {
    return this->addColumn(column, alias);
}

SelectWrapper *SelectWrapper::select(const QList<QString> &columns, const QList<QString> &alias) {
    if (!columns.isEmpty()) {
        if (alias.isEmpty()) {
            return this->select(columns);
        }
        int len = alias.size() < columns.size() ? alias.size() : columns.size();
        for (int i = 0; i < len; ++i) {
            this->select(columns[i], alias[i]);
        }
    }
    return this;
}

void SelectWrapper::consume(const std::function<void(ColumnChain *, ColumnChain *)>& consumer) const {
    consumer(this->_columnStart, this->_columnEnd);
}
