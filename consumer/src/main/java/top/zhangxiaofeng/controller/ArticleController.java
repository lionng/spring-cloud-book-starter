package top.zhangxiaofeng.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ArticleController {

    @Autowired
    private RestTemplate restTemplate;

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/article/callHello")
    public String callHello() {
        return restTemplate.getForObject("http://provider/user/hello", String.class);
    }

    /**
     * 通过EurekaClient取provider的信息
     *
     * @return
     */
    @GetMapping("/article/infos")
    public Object serviceUrl() {
        return eurekaClient.getInstancesByVipAddress("provider", false);
    }

    /**
     * 除了上边通过EurekaClient取provider的信息
     * 还可以通过Spring Cloud封装的
     * @return
     */
    @GetMapping("/article/infoCloud")
    public Object serviceUrlCloud() {
        return discoveryClient.getInstances("provider");
    }

}
