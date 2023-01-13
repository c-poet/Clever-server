package cn.cpoet.clever.core.support;

import io.swagger.v3.oas.annotations.Hidden;

import java.util.HashMap;
import java.util.Map;

/**
 * 针对OpenApi做的适配
 *
 * @author CPoet
 */
public class GashMap<K, V> extends HashMap<K, V> implements Map<K, V> {

    private static final long serialVersionUID = 1489872068682494025L;

    @Override
    @Hidden
    public boolean isEmpty() {
        return super.isEmpty();
    }
}
