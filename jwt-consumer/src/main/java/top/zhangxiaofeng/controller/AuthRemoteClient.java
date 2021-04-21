package top.zhangxiaofeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import top.zhangxiaofeng.common.AuthQuery;
import top.zhangxiaofeng.common.ResponseData;

@Component
public class AuthRemoteClient {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseData auth(AuthQuery query) {
        ResponseEntity<ResponseData> responseDataResponseEntity = restTemplate.postForEntity("http://jwt-auth-service/auth/getToken", query, ResponseData.class);
        ResponseData data = responseDataResponseEntity.getBody();
        return data;
    }
}
