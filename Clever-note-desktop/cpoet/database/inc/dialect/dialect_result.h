// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/1

#ifndef C_POET_DIALECT_RESULT_H
#define C_POET_DIALECT_RESULT_H

#include <QString>
#include <QVariantList>

/// @brief 方言翻译结果
class DialectResult {
private:
    // 生成的sql
    QString _sql;
    // 绑定的参数
    QVariantList *_params;
    // 是否失败
    bool _failed;
    // 失败信息
    QString _message;

public:
    DialectResult();

    ~DialectResult();

    const QString &getSql() const;

    void setSql(const QString &sql);

    const QVariantList *getParams() const;

    void setParams(QVariantList *params);

    bool isFailed() const;

    void setFailed(bool failed);

    const QString &getMessage() const;

    void setMessage(const QString &message);

    DialectResult *appendSql(const char *sql);

    DialectResult *appendSql(const QString &sql);

    DialectResult *addParam(const QVariant &param);

    DialectResult *addParams(const QVariantList &params);
};

#endif
