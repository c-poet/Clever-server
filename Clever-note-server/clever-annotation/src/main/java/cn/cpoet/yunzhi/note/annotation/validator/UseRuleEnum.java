package cn.cpoet.yunzhi.note.annotation.validator;

/**
 * 获取的验证规则来源
 *
 * @author CPoet
 */
public enum UseRuleEnum {
    /**
     * 只读系统
     */
    SYSTEM,
    /**
     * 只读用户
     */
    MEMBER,
    /**
     * 先用户在系统
     */
    SYS_MEMBER
}
