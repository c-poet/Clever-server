// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/11/3

#ifndef C_POET_SELECT_CRITERIA_H
#define C_POET_SELECT_CRITERIA_H

#include "../wrapper/select_wrapper.h"

/// @brief 查询
class SelectCriteria : public SelectWrapper {
public:
    QMap<QString, QVariant> *findOne();

    QList<QMap<QString, QVariant>> *findList();
};

#endif
