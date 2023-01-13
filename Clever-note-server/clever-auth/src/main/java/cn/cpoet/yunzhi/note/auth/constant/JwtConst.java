package cn.cpoet.yunzhi.note.auth.constant;

/**
 * Jwt常量
 *
 * @author CPoet
 */
public interface JwtConst {
    /**
     * 用户id
     */
    String CLAIM_UID = "yunzhi_note@uid";

    /**
     * 用户账号
     */
    String CLAIM_ACCOUNT = "yunzhi_note@account";

    /**
     * 用户组id
     */
    String CLAIM_GROUP_ID = "yunzhi_note@gourp-id";
}
