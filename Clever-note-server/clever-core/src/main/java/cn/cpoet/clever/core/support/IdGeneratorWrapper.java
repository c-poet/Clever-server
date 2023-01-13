package cn.cpoet.clever.core.support;

import cn.cpoet.yunzhi.note.annotation.constant.SystemConst;
import io.ebean.config.IdGenerator;
import lombok.extern.slf4j.Slf4j;

/**
 * id生成器包装
 *
 * @author CPoet
 * @see cn.cpoet.clever.annotation.core.IdGenerator
 * @see IdGenerator
 */
@Slf4j
public class IdGeneratorWrapper implements IdGenerator {
    private final cn.cpoet.clever.annotation.core.IdGenerator<?> thisIdGenerator;

    public IdGeneratorWrapper(cn.cpoet.clever.annotation.core.IdGenerator<?> thisIdGenerator) {
        this.thisIdGenerator = thisIdGenerator;
    }

    @Override
    public String getName() {
        try {
            return thisIdGenerator.getName();
        } catch (Exception e) {
            log.debug("获取ID生成器名称失败：{}", e.getMessage());
        }
        return SystemConst.GLOBAL_ID_GENERATOR;
    }

    @Override
    public Object nextValue() {
        return thisIdGenerator.nextId();
    }
}
