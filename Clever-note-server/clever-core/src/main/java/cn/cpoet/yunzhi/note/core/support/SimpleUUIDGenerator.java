package cn.cpoet.yunzhi.note.core.support;


import cn.cpoet.yunzhi.note.annotation.constant.SystemConst;
import cn.cpoet.yunzhi.note.annotation.core.IdGenerator;
import cn.cpoet.yunzhi.note.core.util.UUIDUtil;

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
