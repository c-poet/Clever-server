package cn.cpoet.clever.core.context;

import cn.cpoet.clever.annotation.context.Subject;
import cn.cpoet.clever.constant.LogicEnum;
import cn.cpoet.clever.constant.SubjectType;

import java.util.List;

public class ProxySubject implements Subject {
    @Override
    public long getUid() {
        return 0;
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
        return null;
    }

    @Override
    public List<String> getRoles() {
        return null;
    }

    @Override
    public List<String> getPermissions() {
        return null;
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
