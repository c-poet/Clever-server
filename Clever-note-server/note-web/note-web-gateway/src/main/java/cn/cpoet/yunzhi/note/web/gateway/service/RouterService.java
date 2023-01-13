package cn.cpoet.yunzhi.note.web.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author CPoet
 */
public interface RouterService {
    /**
     * 获取所有路由列表
     *
     * @return 路由列表
     */
    Flux<RouteDefinition> getRoutes();

    /**
     * 保存路由信息
     *
     * @param route 路由信息
     * @return Void
     */
    Mono<Void> save(Mono<RouteDefinition> route);

    /**
     * 删除路由
     *
     * @param routeId 路由Id
     * @return 删除路由
     */
    Mono<Void> delete(Mono<String> routeId);
}
