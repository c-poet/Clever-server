// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/31

#ifndef C_POET_GROUP_WRAPPER_H
#define C_POET_GROUP_WRAPPER_H

#include "having_wrapper.h"

template<class W>
class GroupWrapper : virtual public HavingWrapper<W> {
private:
    QList<QString> *_columns;

public:
    GroupWrapper() : _columns(nullptr) {};

    virtual ~GroupWrapper() {
        delete this->_columns;
    }

    /// @brief 分组
    /// @param column 参与分组的列
    /// @return this 实例
    W *group(const QString &column) {
        if (this->_columns == nullptr) {
            this->_columns = new QList<QString>();
        }
        this->_columns->append(column);
        return dynamic_cast<W *>(this);
    }

    /// @brief 分组
    /// @param columns 参与分组的列集合
    /// @return this 实例
    W *group(const QList<QString> &columns) {
        if (this->_columns == nullptr) {
            this->_columns = new QList<QString>();
        }
        this->_columns->append(columns);
        return dynamic_cast<W *>(this);
    }

    /// @brief 分组结果消费
    /// @param consumer 消费者
    void consume(const std::function<void(QList<QString> *)> &consumer) {
        consumer(this->_columns);
    }
};

#endif
