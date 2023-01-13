package cn.cpoet.yunzhi.note.web.gateway.feign;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RouterFeignTest {
    @Autowired
    private RouterFeign routerFeign;

    @Test
    void list() {
        System.out.println(routerFeign.list());
    }
}