// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/31

#ifndef C_POET_ORDER_WRAPPER_H
#define C_POET_ORDER_WRAPPER_H

#include "sql_wrapper.h"
#include "order_types.h"

/// @brief 排序Wrapper
template<class W>
class OrderWrapper : virtual public SqlWrapper {
public:
    /// @brief 排序绑定
    class OrderMapperChain {
    private:
        friend class OrderWrapper<W>;

        // 下个节点
        OrderMapperChain *_next;
        // 列名
        QString _column;
        // 排序类型
        OrderTypes _orderType = OrderTypes::ASC;
    public:
        /// @brief 获取列名
        /// @return 列名
        const QString &getColumn() const {
            return _column;
        }

        /// @brief 获取排序类型
        /// @return 排序类型
        OrderTypes getOrderType() const {
            return _orderType;
        }

        /// @brief 获取下一个节点
        /// @return 下一个节点
        OrderMapperChain *getNext() const {
            return _next;
        }
    };

private:
    OrderMapperChain *_start;
    OrderMapperChain *_end;

    OrderMapperChain *newOrderMapperChain(const QString &column, OrderTypes orderType) {
        auto *chain = new OrderMapperChain();
        chain->_next = nullptr;
        chain->_column = column;
        chain->_orderType = orderType;
        return chain;
    }

public:
    OrderWrapper() : _start(nullptr), _end(nullptr) {}

    ~OrderWrapper() override {
        if (this->_start != nullptr) {
            auto *curChain = this->_start;
            OrderMapperChain *swapChain;
            while (curChain != nullptr) {
                swapChain = curChain->_next;
                delete curChain;
                curChain = swapChain;
            }
        }
    }

    /// @brief 正序排序
    /// @param column 排序的列
    /// @return this 实例
    W *asc(const QString &column) {
        return addOrder(column, OrderTypes::ASC);
    }

    /// @brief 正序排序
    /// @param columns 排序的列集合, {"column1", "column2", "column3"}
    /// @return this 实例
    W *asc(const QList<QString> &columns) {
        for (const auto &column: columns) {
            addOrder(column, OrderTypes::ASC);
        }
        return dynamic_cast<W *>(this);
    }

    /// @brief 逆序排序
    /// @param column 排序的列
    /// @return this 实例
    W *desc(const QString &column) {
        return addOrder(column, OrderTypes::DESC);
    }

    /// @brief 逆序排序
    /// @param columns 排序的列集合, {"column1", "column2", "column3"}
    /// @return this 实例
    W *desc(const QList<QString> &columns) {
        for (const auto &column: columns) {
            addOrder(column, OrderTypes::DESC);
        }
        return dynamic_cast<W *>(this);
    }

    /// @brief 添加排序
    /// @param column 排序的列
    /// @param orderType 排序类型
    /// @return this 实例
    W *addOrder(QString column, OrderTypes orderType) {
        auto *newChain = this->newOrderMapperChain(column, orderType);
        if (this->_end == nullptr) {
            this->_start = newChain;
            this->_end = newChain;
        } else {
            this->_end->_next = newChain;
            this->_end = newChain;
        }
        return dynamic_cast<W *>(this);
    }

    /// @brief 排序结果消费
    /// @param consumer 消费者
    void consume(const std::function<void(OrderMapperChain *, OrderMapperChain *)> &consumer) {
        consumer(this->_start, this->_end);
    }
};

#endif
