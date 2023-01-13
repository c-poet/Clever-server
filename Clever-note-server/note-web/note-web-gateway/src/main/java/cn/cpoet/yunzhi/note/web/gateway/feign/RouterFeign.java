package cn.cpoet.yunzhi.note.web.gateway.feign;

import cn.cpoet.yunzhi.note.api.constant.FeignServerConst;
import cn.cpoet.yunzhi.note.web.gateway.dto.RouterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author CPoet
 */
@FeignClient(value = FeignServerConst.COMM, contextId = "RouterFeign")
public interface RouterFeign {
    /**
     * 获取所有可用的路由
     *
     * @return 启用的路由列表
     */
    @RequestMapping(value = "/router/listRouter", method = RequestMethod.POST)
    List<RouterDTO> list();
}
