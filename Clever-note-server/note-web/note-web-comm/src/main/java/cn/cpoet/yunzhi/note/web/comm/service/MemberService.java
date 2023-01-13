package cn.cpoet.yunzhi.note.web.comm.service;

import cn.cpoet.yunzhi.note.api.auth.Subject;
import cn.cpoet.yunzhi.note.web.comm.vo.MemberInfoVO;

/**
 * @author CPoet
 */
public interface MemberService {
    /**
     * 获取基本信息
     *
     * @param subject 主体
     * @return 用户信息
     */
    MemberInfoVO getInfo(Subject subject);
}
