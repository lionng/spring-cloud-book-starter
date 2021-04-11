package top.zhangxiaofeng.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

/**
 * 自定义路由断言工厂需要继承 AbstractRoutePredicateFactory 类，重写 apply 方法的逻辑。
 */
@Component
public class CheckAuthRoutePredicateFactory extends AbstractRoutePredicateFactory<CheckAuthRoutePredicateFactory.Config> {

    Logger logger = LoggerFactory.getLogger(getClass());

    public CheckAuthRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
//            System.err.println("进入了CheckAuthRoutePredicateFactory\t" + config.getName());
            logger.info("进入了CheckAuthRoutePredicateFactory\t{}", config.getName());
            logger.info("CheckAuthRoutePredicateFactory boolean:{}", config.getName().equals("yinjihuan"));
            if (config.getName().equals("yinjihuan")) {
                return true;
            }
            return false;
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
