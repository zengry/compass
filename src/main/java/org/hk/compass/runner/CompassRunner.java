package org.hk.compass.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author zengry
 * @description
 * @since 2019/12/27
 */

@Slf4j
@Component
public class CompassRunner implements ApplicationRunner {

    @Value("${server.port}")
    private Integer port;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("程序启动完成，访问应用地址：http://localhost:" + port);
        log.info("程序启动完成，访问Druid地址：http://localhost:" + port + "/druid/");
        log.info("程序启动完成，访问Swagger2地址：http://localhost:" + port + "/swagger-ui.html");
    }
}
