package cn.cpoet.clever.module.comm.constant;

import cn.cpoet.yunzhi.note.annotation.constant.SystemConst;

/**
 * 常用配置名称
 * <p>约定：系统相关配置名称以{@link cn.cpoet.yunzhi.note.annotation.constant.SystemConst#SETTING_SYS_PREFIX}为前缀，
 * 个人配置以{@link cn.cpoet.yunzhi.note.annotation.constant.SystemConst#SETTING_PERSON_PREFIX}为前缀.</p>
 *
 * @author CPoet
 */
public interface SettingKeys {

    /**
     * 系统站点标题
     */
    String SITE_TITLE = SystemConst.SETTING_SYS_PREFIX + "siteTitle";

    /**
     * 系统站点副标题
     */
    String SITE_SUBTITLE = SystemConst.SETTING_SYS_PREFIX + "siteSubtitle";

    /**
     * 公共密钥
     */
    String COMMON_SECRET = SystemConst.SETTING_SYS_PREFIX + "commonSecret";

    /**
     * 前台注册功能
     */
    String REGISTER_ENABLED = SystemConst.SETTING_SYS_PREFIX + "registerEnabled";

    /**
     * 登录认证信息加密开关
     */
    String LOGIN_INFO_ENCRYPT = SystemConst.SETTING_SYS_PREFIX + "loginInfoEncrypt";

    /**
     * 默认用户组Id
     */
    String DEFAULT_GROUP_ID = SystemConst.SETTING_SYS_PREFIX + "defaultGroupId";

    /**
     * 个人密钥
     */
    String PERSON_SECRET = SystemConst.SETTING_PERSON_PREFIX + "personSecret";
}
