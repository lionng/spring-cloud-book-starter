package top.zhangxiaofeng.config;

import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringBootAdminConfig
 * httptrace:Displays HTTP trace information (by default, the last 100 HTTP request-response exchanges). Requires an HttpTraceRepository bean.
 * 开发中可以使用
 * 生产环境中推荐使用Zipkin or Spring Cloud Sleuth
 * 详情见官方文档  https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-http-tracing
 */
@Configuration
public class SpringBootAdminConfig {

    @Bean
    public InMemoryHttpTraceRepository getInMemoryHttpTrace() {
        return new InMemoryHttpTraceRepository();
    }

}
