// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/31

#ifndef C_POET_SQL_CONDITION_H
#define C_POET_SQL_CONDITION_H

#include <QVariant>
#include "sql_wrapper.h"
#include "sql_condition_heads.h"
#include "sql_condition_types.h"
#include "sql_condition_logic.h"
#include "sql_condition_region.h"

class SqlCondition {
private:
    // 表达式类型
    SqlConditionTypes _type;
public:
    SqlCondition();

    explicit SqlCondition(const SqlConditionTypes &type);

    virtual ~SqlCondition() = default;

    /// @brief 获取支持的类型
    /// @return 支持的类型
    SqlConditionTypes getType() const;

    /// @brief 设置支持的类型
    /// @param type 支持的类型
    void setType(SqlConditionTypes type);

    /// @brief 表达式实例类型
    /// @return 表达式实例类型
    virtual SqlConditionHeads head() = 0;
};

/// @brief 一目表达式
class UnaryCondition : public SqlCondition {
private:
    bool _col;
    QVariant _value;

public:
    explicit UnaryCondition(const SqlConditionTypes &type);


    SqlConditionHeads head() override {
        return SqlConditionHeads::UNARY;
    }

    bool isCol() const;

    UnaryCondition *setCol(bool col);

    const QVariant &getValue() const;

    UnaryCondition *setValue(const QVariant &value);
};

/// @brief 二目表达式
class BinaryCondition : public SqlCondition {
private:
    bool _leftCol;
    QVariant _leftValue;

    bool _rightCol;
    QVariant _rightValue;

public:
    explicit BinaryCondition(const SqlConditionTypes &type);

    SqlConditionHeads head() override {
        return SqlConditionHeads::BINARY;
    }

    bool isLeftCol() const;

    BinaryCondition *setLeftCol(bool leftCol);

    const QVariant &getLeftValue() const;

    BinaryCondition *setLeftValue(const QVariant &leftValue);

    bool isRightCol() const;

    BinaryCondition *setRightCol(bool rightCol);

    const QVariant &getRightValue() const;

    BinaryCondition *setRightValue(const QVariant &rightValue);
};

/// @brief 三目表达式
class TernaryCondition : public SqlCondition {
private:
    bool _upperCol;
    QVariant _upperValue;

    bool _middleCol;
    QVariant _middleValue;

    bool _lowerCol;
    QVariant _lowerValue;

public:
    explicit TernaryCondition(const SqlConditionTypes &type);

    SqlConditionHeads head() override {
        return SqlConditionHeads::TERNARY;
    }

    bool isUpperCol() const;

    TernaryCondition *setUpperCol(bool upperCol);

    const QVariant &getUpperValue() const;

    TernaryCondition *setUpperValue(const QVariant &upperValue);

    bool isMiddleCol() const;

    TernaryCondition *setMiddleCol(bool middleCol);

    const QVariant &getMiddleValue() const;

    TernaryCondition *setMiddleValue(const QVariant &middleValue);

    bool isLowerCol() const;

    TernaryCondition *setLowerCol(bool lowerCol);

    const QVariant &getLowerValue() const;

    TernaryCondition *setLowerValue(const QVariant &lowerValue);
};

/// @brief Wrapper类型
class SqlWrapperCondition : public SqlCondition {
private:
    SqlWrapper *_wrapper;

public:
    explicit SqlWrapperCondition(SqlWrapper *wrapper);

    ~SqlWrapperCondition() override;

    SqlWrapper *getWrapper() const;

    SqlConditionHeads head() override {
        return SqlConditionHeads::WRAPPER;
    }
};

/// @brief 逻辑表达式
class SqlLogicCondition : public SqlCondition {
private:
    SqlConditionLogic _logic;
public:
    explicit SqlLogicCondition(SqlConditionLogic logic);

    SqlConditionLogic getLogic() const;

    SqlConditionHeads head() override {
        return SqlConditionHeads::LOGIC;
    }
};

/// @brief 界表达式
class SqlRegionCondition : public SqlCondition {
private:
    SqlConditionRegion _region;
public:
    explicit SqlRegionCondition(SqlConditionRegion region);

    SqlConditionRegion getRegion() const;

    SqlConditionHeads head() override {
        return SqlConditionHeads::REGION;
    }
};

#endif
