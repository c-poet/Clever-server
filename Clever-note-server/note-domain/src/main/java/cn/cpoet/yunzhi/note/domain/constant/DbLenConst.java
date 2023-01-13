package cn.cpoet.yunzhi.note.domain.constant;

/**
 * 常用字段长度定义
 *
 * @author CPoet
 */
public interface DbLenConst {
    /**
     * 长：32
     */
    int L32 = 32;

    /**
     * 长：50
     */
    int L50 = 50;

    /**
     * 长：128
     */
    int L128 = 128;

    /**
     * 长：255
     */
    int L255 = 255;

    /**
     * 长：256
     */
    int L256 = 256;

    /**
     * 长：512
     */
    int L512 = 512;

    /**
     * 长：800
     */
    int L800 = 800;

    /**
     * 长：1024
     */
    int L1024 = 1024;

    /**
     * 姓名
     */
    int STAFF_NAME = L32;

    /**
     * 账号
     */
    int ACCOUNT = L50;

    /**
     * 手机号
     */
    int MOBILE = L50;

    /**
     * 电话
     */
    int TELEPHONE = L50;

    /**
     * 邮箱
     */
    int EMAIL = L128;

    /**
     * url地址
     */
    int URL = L512;

    /**
     * 头像地址
     */
    int AVATAR = URL;

    /**
     * IP地址
     */
    int IP = L128;
}
