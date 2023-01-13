// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/31

#ifndef C_POET_UPDATE_WRAPPER_H
#define C_POET_UPDATE_WRAPPER_H

#include "where_wrapper.h"

/// @brief 更新Wrapper
class UpdateWrapper : public WhereWrapper<UpdateWrapper> {
public:
    /// @brief 更新列
    class UpdateMapperChain {
    private:
        friend class UpdateWrapper;

        UpdateMapperChain *_next;
        QString _column;
        QVariant _val;
        bool _col;
    public:
        const QString &getColumn() const;

        UpdateMapperChain *getNext() const;

        const QVariant &getVal() const;

        bool isCol() const;
    };

private:
    QString _table;

    UpdateMapperChain *_start;
    UpdateMapperChain *_end;

    UpdateMapperChain *newUpdateMapperChain(const QString &column, const QVariant &val, bool isCol);

public:
    UpdateWrapper();

    /// @brief 设置表名
    /// @param table 表名
    explicit UpdateWrapper(const QString &table);

    ~UpdateWrapper() override;

    /// @brief 获取表名
    /// @return 表名
    const QString &getTable() const;

    /// @brief 设置表名
    /// @param table 表名
    /// @return this 实例
    UpdateWrapper *setTable(const QString &table);

    /// @brief 设置列值
    /// @param column 列名
    /// @param val 列值
    /// @return this 实例
    UpdateWrapper *set(const QString &column, const QVariant &val);

    /// @brief 设置列值
    /// @param column 列名
    /// @param val 列值
    /// @param isCol 是否所属列
    /// @return this 实例
    UpdateWrapper *set(const QString &column, const QVariant &val, bool isCol);

    /// @brief 重新设置列值
    /// @param column 列名
    /// @param val 列值
    /// @return this 实例
    UpdateWrapper *reset(const QString &column, const QVariant &val);

    /// @brief 重新设置列值
    /// @param column 列名
    /// @param val 列值
    /// @param isCol 是否所属列
    /// @return this 实例
    UpdateWrapper *reset(const QString &column, const QVariant &val, bool isCol);

    /// @brief 结果消费
    /// @param consumer 消费者
    void consume(const std::function<void(UpdateMapperChain *, UpdateMapperChain *)> &consumer) const;

    SqlWrapperTypes type() override {
        return SqlWrapperTypes::UPDATE;
    }
};

#endif
