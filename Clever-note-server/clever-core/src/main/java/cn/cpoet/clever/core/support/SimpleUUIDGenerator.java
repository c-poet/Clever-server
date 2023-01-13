package cn.cpoet.clever.core.support;


import cn.cpoet.yunzhi.note.annotation.constant.SystemConst;
import cn.cpoet.clever.annotation.core.IdGenerator;
import cn.cpoet.clever.util.UUIDUtil;

/**
 * 默认uuid实现
 *
 * @author wanggf
 */
public class SimpleUUIDGenerator implements IdGenerator<String> {
    /**
     * 全局公用
     */
    public final static SimpleUUIDGenerator INSTANCE = new SimpleUUIDGenerator();

    @Override
    public String getName() {
        return SystemConst.GLOBAL_UUID_GENERATOR;
    }

    @Override
    public String nextId() {
        return UUIDUtil.randomPure();
    }
}
