package top.zhangxiaofeng.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.zhangxiaofeng.common.AuthQuery;
import top.zhangxiaofeng.common.ResponseData;
import top.zhangxiaofeng.entity.User;
import top.zhangxiaofeng.service.AuthService;
import top.zhangxiaofeng.utils.JWTUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/getToken")
    public ResponseData auth(@RequestBody AuthQuery query) {
        if (StringUtils.isBlank(query.getAccessKey()) || StringUtils.isBlank(query.getSecretKey())) {
            return ResponseData.failByParam("accessKey and secretKey not null");
        }
        User user = authService.auth(query);
        if (user == null) {
            return ResponseData.failByParam(" 认证失败 ");
        }
        JWTUtils jwt = JWTUtils.getInstance();
        return ResponseData.ok(jwt.getToken(user.getId().toString()));
    }

}
