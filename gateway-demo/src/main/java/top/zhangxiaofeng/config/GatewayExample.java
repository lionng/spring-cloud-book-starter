package top.zhangxiaofeng.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Spring Cloud Gateway实战案例
 */
//@Component
public class GatewayExample {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * IP 限流
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> {
            logger.info("GatewayExample HostName:{}", exchange.getRequest().getRemoteAddress().getHostName());
            return Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
        };
    }

    public static String getIpAddr(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        List<String> ips = headers.get("X-Forwarded-For");
        String ip = "192.168.50.166";
        if (ips != null && ips.size() > 0) {
            ip = ips.get(0);
        }
        return ip;
    }

    /**
     * 用户限流
     * @return
     */
    @Bean
    KeyResolver userKeyResolver() {
        return exchange ->
                Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
    }

    /**
     * 接口限流
     * @return
     */
    @Bean
    KeyResolver apiKeyResolver() {
        return exchange ->
                Mono.just(exchange.getRequest().getPath().value());
    }
}
