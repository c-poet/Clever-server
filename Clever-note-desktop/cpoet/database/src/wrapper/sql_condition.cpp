// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/31

#include <qvariant.h>
#include "inc/wrapper/sql_condition.h"

SqlCondition::SqlCondition() : _type(SqlConditionTypes::NONE) {

}

SqlConditionTypes SqlCondition::getType() const {
    return _type;
}

void SqlCondition::setType(SqlConditionTypes type) {
    _type = type;
}

SqlCondition::SqlCondition(const SqlConditionTypes &type) :
        _type(type) {
}


UnaryCondition::UnaryCondition(const SqlConditionTypes &type) :
        SqlCondition(type),
        _col(true) {
}

bool UnaryCondition::isCol() const {
    return _col;
}

UnaryCondition *UnaryCondition::setCol(bool col) {
    _col = col;
    return this;
}

const QVariant &UnaryCondition::getValue() const {
    return _value;
}

UnaryCondition *UnaryCondition::setValue(const QVariant &value) {
    _value = value;
    return this;
}

BinaryCondition::BinaryCondition(const SqlConditionTypes &type) :
        SqlCondition(type),
        _leftCol(true), _rightCol(false) {
}

bool BinaryCondition::isLeftCol() const {
    return _leftCol;
}

BinaryCondition *BinaryCondition::setLeftCol(bool leftCol) {
    _leftCol = leftCol;
    return this;
}

const QVariant &BinaryCondition::getLeftValue() const {
    return _leftValue;
}

BinaryCondition *BinaryCondition::setLeftValue(const QVariant &leftValue) {
    _leftValue = leftValue;
    return this;
}

bool BinaryCondition::isRightCol() const {
    return _rightCol;
}

BinaryCondition *BinaryCondition::setRightCol(bool rightCol) {
    _rightCol = rightCol;
    return this;
}

const QVariant &BinaryCondition::getRightValue() const {
    return _rightValue;
}

BinaryCondition *BinaryCondition::setRightValue(const QVariant &rightValue) {
    _rightValue = rightValue;
    return this;
}

TernaryCondition::TernaryCondition(const SqlConditionTypes &type) :
        SqlCondition(type),
        _upperCol(true),
        _middleCol(false),
        _lowerCol(false) {
}

bool TernaryCondition::isUpperCol() const {
    return _upperCol;
}

TernaryCondition *TernaryCondition::setUpperCol(bool upperCol) {
    _upperCol = upperCol;
    return this;
}

const QVariant &TernaryCondition::getUpperValue() const {
    return _upperValue;
}

TernaryCondition *TernaryCondition::setUpperValue(const QVariant &upperValue) {
    _upperValue = upperValue;
    return this;
}

bool TernaryCondition::isMiddleCol() const {
    return _middleCol;
}

TernaryCondition *TernaryCondition::setMiddleCol(bool middleCol) {
    _middleCol = middleCol;
    return this;
}

const QVariant &TernaryCondition::getMiddleValue() const {
    return _middleValue;
}

TernaryCondition *TernaryCondition::setMiddleValue(const QVariant &middleValue) {
    _middleValue = middleValue;
    return this;
}

bool TernaryCondition::isLowerCol() const {
    return _lowerCol;
}

TernaryCondition *TernaryCondition::setLowerCol(bool lowerCol) {
    _lowerCol = lowerCol;
    return this;
}

const QVariant &TernaryCondition::getLowerValue() const {
    return _lowerValue;
}

TernaryCondition *TernaryCondition::setLowerValue(const QVariant &lowerValue) {
    _lowerValue = lowerValue;
    return this;
}

SqlWrapperCondition::SqlWrapperCondition(SqlWrapper *wrapper) :
        SqlCondition(SqlConditionTypes::WRAPPER),
        _wrapper(wrapper) {
}

SqlWrapperCondition::~SqlWrapperCondition() {
    delete this->_wrapper;
}

SqlWrapper *SqlWrapperCondition::getWrapper() const {
    return _wrapper;
}

SqlLogicCondition::SqlLogicCondition(SqlConditionLogic logic) : _logic(logic) {
}

SqlConditionLogic SqlLogicCondition::getLogic() const {
    return _logic;
}

SqlRegionCondition::SqlRegionCondition(SqlConditionRegion region) : _region(region) {
}

SqlConditionRegion SqlRegionCondition::getRegion() const {
    return _region;
}
