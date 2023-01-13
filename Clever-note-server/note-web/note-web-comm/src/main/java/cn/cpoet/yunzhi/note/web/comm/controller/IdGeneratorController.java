package cn.cpoet.yunzhi.note.web.comm.controller;

import cn.cpoet.yunzhi.note.api.feign.FeignTarget;
import cn.cpoet.yunzhi.note.core.support.SnowFlakeIdGenerator;
import cn.cpoet.yunzhi.note.core.feign.IdGeneratorFeign;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Id生成器
 *
 * @author CPoet
 */
@Primary
@RestController
@RequiredArgsConstructor
@RequestMapping("/id-generator")
@Tag(name = "IdGenerator", description = "Id生成器-采用雪花算法")
public class IdGeneratorController implements IdGeneratorFeign {
    private final SnowFlakeIdGenerator idGenerator;

    @Override
    @FeignTarget
    @Operation(summary = "获取Id生成器名称")
    public String getName() {
        return idGenerator.getName();
    }

    @Override
    @FeignTarget
    @Operation(summary = "获取唯一Id")
    public Long nextId() {
        return idGenerator.nextId();
    }
}
