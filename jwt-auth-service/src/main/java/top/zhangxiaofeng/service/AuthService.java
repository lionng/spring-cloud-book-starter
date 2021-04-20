package top.zhangxiaofeng.service;

import top.zhangxiaofeng.common.AuthQuery;
import top.zhangxiaofeng.entity.User;

public interface AuthService {

    User auth(AuthQuery query);
}
