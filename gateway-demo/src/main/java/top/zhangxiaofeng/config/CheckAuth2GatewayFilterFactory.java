package top.zhangxiaofeng.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * 自定义过滤器工厂(一)
 * 自定义 Spring Cloud Gateway 过滤器工厂需要继承 AbstractGatewayFilterFactory 类，重写 apply 方法的逻辑。命名需要以 GatewayFilterFactory 结尾，
 * 比如 CheckAuthGatewayFilterFactory，那么在使用的时候 CheckAuth 就是这个过滤器工厂的名称。
 */
@Component
public class CheckAuth2GatewayFilterFactory extends AbstractGatewayFilterFactory<CheckAuth2GatewayFilterFactory.Config> {

    Logger logger = LoggerFactory.getLogger(getClass());

    public CheckAuth2GatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
//            System.err.println("进入了CheckAuth2GatewayFilterFactory" + config.getName());
            logger.info("进入了CheckAuth2GatewayFilterFactory\t{}", config.getName());
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .build();
            return
                    chain.filter(exchange.mutate().request(request).build());
        };
    }

    public static class Config {
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
