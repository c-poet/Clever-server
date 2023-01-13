// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/31

#ifndef C_POET_INSERT_WRAPPER
#define C_POET_INSERT_WRAPPER

#include <QString>
#include "sql_wrapper.h"

class QVariant;

typedef QList<QVariant> QVariantList;


/// @brief 插入Wrapper
class InsertWrapper : public SqlWrapper {
private:
    QString _table;
    QList<QString> *_columns;
    QVariantList *_values;
public:
    InsertWrapper();

    explicit InsertWrapper(const QString &table);

    ~InsertWrapper() override;

    /// @brief 获取表名
    /// @return 表名
    const QString &getTable() const;

    /// @brief 设置表名
    /// @param table 表名
    /// @return this 实例
    InsertWrapper *setTable(const QString &table);

    /// @brief 设置null值
    /// @param column 列名
    /// @return this 实例
    InsertWrapper *setNull(const QString &column);

    /// @brief 设置列值
    /// @param column 列名
    /// @param value 列值
    /// @return this 实例
    InsertWrapper *set(const QString &column, const QVariant &value);

    /// @brief 插入结果消费
    /// @param consumer 消费者
    void consume(const std::function<void(QList<QString> *, QVariantList *)> &consumer);

    SqlWrapperTypes type() override {
        return SqlWrapperTypes::INSERT;
    }
};

#endif
