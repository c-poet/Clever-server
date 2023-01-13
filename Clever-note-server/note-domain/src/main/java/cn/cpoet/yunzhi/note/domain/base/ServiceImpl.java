package cn.cpoet.yunzhi.note.domain.base;

import cn.cpoet.yunzhi.note.annotation.constant.SystemConst;

/**
 * @author CPoet
 */
public class ServiceImpl<ENTITY extends BaseRecordModel>
        extends IBaseServiceImpl<ENTITY, Long> implements BaseService<ENTITY> {

    @Override
    public boolean isPersistId(Long id) {
        return id != null && (id == SystemConst.SYS_ID || id == SystemConst.GUEST_ID);
    }
}
