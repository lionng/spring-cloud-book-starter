package top.zhangxiaofeng.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TokenInterceptor implements ClientHttpRequestInterceptor {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        logger.info("TokenInterceptor 进入RestTemplate拦截器");
//        System.err.println("进入RestTemplate拦截器");
        HttpHeaders headers = request.getHeaders();
        String token = System.getProperty("fangjia.auth.token");
        logger.info("TokenInterceptor fangjia.auth.token:{}", token);
        headers.add("Authorization", token);
        return execution.execute(request, body);
    }
}