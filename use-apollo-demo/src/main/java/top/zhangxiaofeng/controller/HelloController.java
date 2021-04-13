package top.zhangxiaofeng.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @ApolloConfig
    private Config config;

    @GetMapping("/config/getUserName3")
    public String getUserName3() {
        return config.getProperty("username", "zhangsan");
    }
}
