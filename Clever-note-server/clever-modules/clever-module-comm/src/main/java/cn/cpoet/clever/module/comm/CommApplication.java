package cn.cpoet.yunzhi.note.web.comm;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author CPoet
 */
@SpringBootApplication
public class CommApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CommApplication.class)
            .build(args)
            .run();
    }
}
