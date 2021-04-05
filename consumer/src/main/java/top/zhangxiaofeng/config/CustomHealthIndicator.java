package top.zhangxiaofeng.config;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

//@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

    /**
     * 修改actuator的health状态, UP --> DOWN,状态从可用变成不可用
     * @param builder
     * @throws Exception
     */
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.down().withDetail("status", false);
    }

}
