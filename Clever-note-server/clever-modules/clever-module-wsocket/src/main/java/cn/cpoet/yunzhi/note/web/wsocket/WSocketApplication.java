package cn.cpoet.yunzhi.note.web.wsocket;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @author CPoet
 */
@EnableWebSocket
@SpringBootApplication
public class WSocketApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(WSocketApplication.class)
            .build(args)
            .run();
    }
}
