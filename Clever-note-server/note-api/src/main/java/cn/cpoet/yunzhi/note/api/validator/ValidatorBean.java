package cn.cpoet.yunzhi.note.api.validator;

import lombok.Data;

import java.io.Serializable;

/**
 * 验证信息
 *
 * @author CPoet
 */
@Data
public class ValidatorBean implements Serializable {

    private static final long serialVersionUID = 7286845955277470936L;

    /**
     * 验证规则
     */
    private String rule;

    /**
     * 类型
     */
    private DynamicRuleType ruleType;

    /**
     * 提示信息
     */
    private String message;
}
