package cn.cpoet.yunzhi.note.core.feign;

import cn.cpoet.yunzhi.note.annotation.constant.FeignServerConst;
import cn.cpoet.yunzhi.note.core.query.IdQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
 * 人员基本信息远程调用
 *
 * @author CPoet
 */
@FeignClient(value = FeignServerConst.COMM, contextId = "MemberFeign", path = "/member", primary = false)
public interface MemberFeign {
    /**
     * 获取角色列表
     *
     * @param idQuery 用户uid
     * @return 角色列表
     */
    @RequestMapping(value = "/listRole", method = RequestMethod.POST)
    Set<String> listRole(@RequestBody @Validated IdQuery idQuery);

    /**
     * 获取权限列表
     *
     * @param idQuery 用户uid
     * @return 权限列表
     */
    @RequestMapping(value = "/listPermission", method = RequestMethod.POST)
    Set<String> listPermission(@RequestParam @Validated IdQuery idQuery);
}
