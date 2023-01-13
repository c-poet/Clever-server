package cn.cpoet.clever.core.feign;

import cn.cpoet.clever.annotation.IdGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Id生成器
 *
 * @author CPoet
 */
//@FeignClient(value = FeignServerConst.COMM, contextId = "IdGeneratorFeign", path = "/id-generator", primary = false)
public interface IdGeneratorFeign extends IdGenerator<Long> {
    /**
     * 获取生成器的名称
     *
     * @return 生成器名称
     */
    @Override
    @RequestMapping(value = "/getName", method = RequestMethod.POST)
    String getName();

    /**
     * 获取有效ID
     *
     * @return 有效ID
     */
    @Override
    @RequestMapping(value = "/next", method = RequestMethod.POST)
    Long nextId();
}
