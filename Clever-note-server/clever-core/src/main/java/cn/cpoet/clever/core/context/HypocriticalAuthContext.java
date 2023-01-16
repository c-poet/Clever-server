package cn.cpoet.clever.core.context;

import cn.cpoet.clever.annotation.RequestWrapper;
import cn.cpoet.clever.annotation.context.AuthContext;
import cn.cpoet.clever.annotation.context.Subject;

public class HypocriticalAuthContext implements AuthContext {
    @Override
    public Subject getSubject() {
        return null;
    }

    @Override
    public Subject getSubject(RequestWrapper request) {
        return null;
    }

    @Override
    public RequestWrapper getDefaultRequestWrapper() {
        return null;
    }
}
