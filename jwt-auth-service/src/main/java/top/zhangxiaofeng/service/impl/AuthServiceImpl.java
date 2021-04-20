package top.zhangxiaofeng.service.impl;

import org.springframework.stereotype.Service;
import top.zhangxiaofeng.common.AuthQuery;
import top.zhangxiaofeng.entity.User;
import top.zhangxiaofeng.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public User auth(AuthQuery query) {
        if ("username".equals(query.getAccessKey()) && "password".equals(query.getSecretKey())) {
            User user = new User();
            user.setId(1);
            user.setUsername("username");
            user.setPassword("password");
            return user;
        }
        return null;
    }

}
