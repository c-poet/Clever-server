package cn.cpoet.clever.standalone;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author CPoet
 */
@SpringBootApplication
public class CleverStandaloneApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CleverStandaloneApplication.class)
            .bannerMode(Banner.Mode.OFF)
            .build(args)
            .run();
    }
}
