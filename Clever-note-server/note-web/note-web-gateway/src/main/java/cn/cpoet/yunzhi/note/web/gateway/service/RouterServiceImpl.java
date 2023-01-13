package cn.cpoet.yunzhi.note.web.gateway.service;

import cn.cpoet.yunzhi.note.web.gateway.dto.RouterDTO;
import cn.cpoet.yunzhi.note.web.gateway.feign.RouterFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CPoet
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RouterServiceImpl implements RouterService, ApplicationListener<ApplicationReadyEvent> {

    private RouterFeign routerFeign;

    private final ThreadLocal<Boolean> reentrancyCache = ThreadLocal.withInitial(() -> Boolean.FALSE);

    @Override
    public Flux<RouteDefinition> getRoutes() {
        if (routerFeign != null) {
            // 这里有个线程重入的问题，由Feign和Router的依赖关系引起
            // 因此这里将对重入的线程进行过滤，避免循环引起服务宕机
            Boolean isReentrancy = reentrancyCache.get();
            if (Boolean.FALSE.equals(isReentrancy)) {
                reentrancyCache.set(Boolean.TRUE);
                log.info("Query remote routing information.");
                try {
                    List<RouterDTO> routers = routerFeign.list();
                    if (!CollectionUtils.isEmpty(routers)) {
                        return Flux
                            .fromIterable(routers)
                            .map(this::transform2routeDefinition);
                    }
                } catch (Exception e) {
                    log.warn("Remote routing information query failed: {}", e.getMessage());
                } finally {
                    reentrancyCache.remove();
                }
            }
        }
        return Flux.empty();
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        log.info("Save route to remote.");
        return Mono.empty();
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        log.info("Remove remote routes.");
        return Mono.empty();
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        this.routerFeign = event
            .getApplicationContext()
            .getBean(RouterFeign.class);
    }

    /**
     * 路由信息适配转换
     *
     * @param router 路由信息Bean
     * @return 适配的路由信息
     */
    private RouteDefinition transform2routeDefinition(RouterDTO router) {
        RouteDefinition route = new RouteDefinition();
        route.setId(String.valueOf(router.getId()));
        route.setUri(URI.create(router.getUri()));
        route.setOrder(router.getSorted());
        route.setMetadata(CollectionUtils.isEmpty(router.getMetadata()) ? Collections.emptyMap() : router.getMetadata());
        List<String> predicates = router.getPredicates();
        if (!CollectionUtils.isEmpty(predicates)) {
            List<PredicateDefinition> predicateDefinitions = predicates
                .stream()
                .map(PredicateDefinition::new)
                .collect(Collectors.toList());
            route.setPredicates(predicateDefinitions);
        }
        List<String> filters = router.getFilters();
        if (!CollectionUtils.isEmpty(filters)) {
            List<FilterDefinition> filterDefinitions = filters
                .stream()
                .map(FilterDefinition::new)
                .collect(Collectors.toList());
            route.setFilters(filterDefinitions);
        }
        return route;
    }
}
