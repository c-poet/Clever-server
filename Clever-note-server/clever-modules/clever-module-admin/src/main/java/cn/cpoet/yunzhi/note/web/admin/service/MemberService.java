package cn.cpoet.yunzhi.note.web.admin.service;

import cn.cpoet.clever.core.vo.PageVO;
import cn.cpoet.yunzhi.note.web.admin.query.MemberQuery;
import cn.cpoet.yunzhi.note.web.admin.vo.MemberVO;

import java.util.List;

/**
 * @author CPoet
 */
public interface MemberService {
    /**
     * 根据用户id查询
     *
     * @param id 用户id
     * @return 用户信息
     */
    MemberVO getById(Long id);

    /**
     * 分页查询用户信息
     *
     * @param memberQuery 查询参数
     * @return 用户列表
     */
    PageVO<MemberVO> list(MemberQuery memberQuery);

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     */
    void deleteById(List<Long> id);
}
