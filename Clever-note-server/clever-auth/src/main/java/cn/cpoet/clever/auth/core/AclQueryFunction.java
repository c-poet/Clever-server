package cn.cpoet.clever.auth.core;

import java.util.Collection;
import java.util.function.Function;

/**
 * 用户权限查询
 *
 * @author CPoet
 */
@FunctionalInterface
public interface AclQueryFunction extends Function<Long, Collection<String>> {
}
