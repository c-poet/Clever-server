package cn.cpoet.yunzhi.note.auth.core;

import cn.cpoet.yunzhi.note.annotation.constant.SubjectType;
import cn.cpoet.yunzhi.note.annotation.constant.SystemConst;

/**
 * 游客主体
 *
 * @author CPoet
 */
public class GuestSubject extends SysSubject {

    private static final long serialVersionUID = -7087431674747133320L;

    public final static GuestSubject INSTANCE = new GuestSubject();

    GuestSubject() {
    }

    @Override
    public long getUid() {
        return SystemConst.GUEST_ID;
    }

    @Override
    public SubjectType getType() {
        return SubjectType.GUEST;
    }
}
