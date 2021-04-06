package top.zhangxiaofeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class HystrixFeignDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixFeignDemoApplication.class, args);
    }

}
