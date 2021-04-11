package top.zhangxiaofeng.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/**
 *定义了 3 个 GlobalFilter，通过 @Order 来指定执行的顺序，数字越小，优先级越高。下面就是输出的日志，从日志就可以看出执行的顺序
 *
 * 2021-04-11 20:08:22.138  INFO 12148 --- [ctor-http-nio-3] uration$$EnhancerBySpringCGLIB$$d1d0c699 : first pre filter
 * 2021-04-11 20:08:22.138  INFO 12148 --- [ctor-http-nio-3] uration$$EnhancerBySpringCGLIB$$d1d0c699 : second pre filter
 * 2021-04-11 20:08:22.138  INFO 12148 --- [ctor-http-nio-3] uration$$EnhancerBySpringCGLIB$$d1d0c699 : third pre filter
 * 2021-04-11 20:08:22.315  INFO 12148 --- [ctor-http-nio-4] uration$$EnhancerBySpringCGLIB$$d1d0c699 : first post filter
 * 2021-04-11 20:08:22.315  INFO 12148 --- [ctor-http-nio-4] uration$$EnhancerBySpringCGLIB$$d1d0c699 : second post filter
 * 2021-04-11 20:08:22.315  INFO 12148 --- [ctor-http-nio-4] uration$$EnhancerBySpringCGLIB$$d1d0c699 : third post filter
 */
//@Configuration
public class ExampleConfiguration {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    @Order(-1)
    public GlobalFilter a() {
        return (exchange, chain) -> {
            logger.info("first pre filter");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("third post filter");
            }));
        };
    }

    @Bean
    @Order(0)
    public GlobalFilter b() {
        return (exchange, chain) -> {
            logger.info("second pre filter");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("second post filter");
            }));
        };
    }

    @Bean
    @Order(1)
    public GlobalFilter c() {
        return (exchange, chain) -> {
            logger.info("third pre filter");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("first post filter");
            }));
        };
    }
}

