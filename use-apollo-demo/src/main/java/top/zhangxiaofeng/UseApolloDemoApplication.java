package top.zhangxiaofeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UseApolloDemoApplication {

    public static void main(String[] args) {
        // 指定环境(仅供开发演示用, 不能用于生产环境))
        System.setProperty("env", "DEV");
        SpringApplication.run(UseApolloDemoApplication.class, args);
    }

}
