package top.zhangxiaofeng.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhangxiaofeng.dto.UserDto;
import top.zhangxiaofeng.vo.AddUserParam;

@RestController
@RequestMapping("/swagger")
public class SwaggerController {

    @PostMapping("/user")
    public UserDto addUser(@RequestBody AddUserParam param) {
        System.err.println(param.getName());
        return new UserDto();
    }
}
