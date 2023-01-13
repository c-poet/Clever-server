// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/31

#ifndef C_POET_SELECT_WRAPPER_H
#define C_POET_SELECT_WRAPPER_H

#include "where_wrapper.h"
#include "group_wrppaer.h"
#include "order_wrapper.h"
#include "join_wrapper.h"


class SelectWrapper :
        public WhereWrapper<SelectWrapper>,
        public JoinWrapper<SelectWrapper>,
        public GroupWrapper<SelectWrapper>,
        public OrderWrapper<SelectWrapper> {
public:
    /// @brief 查询列信息
    class ColumnChain {
    private:
        friend class SelectWrapper;

        // 列名
        QString _name;
        //  列别名
        QString _alias;
        // 下个节点
        ColumnChain *_next;
    public:
        const QString &getName() const;

        ColumnChain *setName(const QString &name);

        const QString &getAlias() const;

        ColumnChain *setAlias(const QString &alias);

        ColumnChain *getNext() const;
    };

private:
    QString _table;
    QString _alias;

    ColumnChain *_columnStart;
    ColumnChain *_columnEnd;

    SelectWrapper *addColumn(const QString &column, const QString &alias = QString());

public:
    SelectWrapper();

    explicit SelectWrapper(const QString &table);

    SelectWrapper(const QString &table, const QString &alias);

    ~SelectWrapper() override;

    SqlWrapperTypes type() override {
        return SqlWrapperTypes::QUERY;
    }

    const QString &getTable() const;

    SelectWrapper *setTable(const QString &table);

    const QString &getAlias() const;

    SelectWrapper *setAlias(const QString &alias);

    SelectWrapper *select(const QString &column);

    SelectWrapper *select(const QList<QString> &columns);

    SelectWrapper *select(const QString &column, const QString &alias);

    SelectWrapper *select(const QList<QString> &columns, const QList<QString> &alias);

    void consume(const std::function<void(ColumnChain *, ColumnChain *)> &consumer) const;
};

#endif
