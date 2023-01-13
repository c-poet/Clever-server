package cn.cpoet.clever.cloud;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author CPoet
 */
@SpringBootApplication
public class CleverGatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CleverGatewayApplication.class)
            .bannerMode(Banner.Mode.OFF)
            .build(args)
            .run();
    }
}
