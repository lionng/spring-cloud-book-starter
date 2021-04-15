package top.zhangxiaofeng.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${server.port}")
    private String port;

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/user/hello")
    public String hello(HttpServletRequest request) {
        logger.info("UserController hello port:{}", port);
        Date date = new Date();
        return "hello    applicationName:" + applicationName + "     port:" + port + "  date:" + date;
    }

}
