package cn.cpoet.yunzhi.note.auth.core;

import cn.cpoet.yunzhi.note.api.auth.Subject;
import cn.cpoet.yunzhi.note.api.constant.LogicEnum;
import cn.cpoet.yunzhi.note.api.constant.SubjectType;
import cn.cpoet.yunzhi.note.api.constant.SystemConst;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * 系统主体
 *
 * @author CPoet
 */
public class SysSubject implements Subject, Serializable {

    private static final long serialVersionUID = -1728106068056808444L;

    public final static SysSubject INSTANCE = new SysSubject();

    SysSubject() {
    }

    @Override
    public long getUid() {
        return SystemConst.SYS_ID;
    }

    @Override
    public long getGroupId() {
        return 0;
    }

    @Override
    public String getAccount() {
        return null;
    }

    @Override
    public SubjectType getType() {
        return SubjectType.SYSTEM;
    }

    @Override
    public Collection<String> getRoles() {
        return Collections.emptyList();
    }

    @Override
    public Collection<String> getPermissions() {
        return Collections.emptyList();
    }

    @Override
    public boolean hasRole(LogicEnum logic, String... roles) {
        return false;
    }

    @Override
    public boolean hasPermission(LogicEnum logic, String... permissions) {
        return false;
    }
}
