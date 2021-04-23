package top.zhangxiaofeng.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import top.zhangxiaofeng.dto.UserDto;
import top.zhangxiaofeng.vo.AddUserParam;

@Api(tags = {"第一个swagger"})
@RestController
@RequestMapping("/hello")
public class HelloController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/index")
    public String index() {
        return "index method";
    }

    /**
     * @ApiOperation：注解来给API增加方法说明
     * @ApiParam 用于 Controller 中方法的参数说明--value：参数说明;required：是否必填
     */
    @ApiOperation(value = "新增用户", notes = "详细描述")
    @PostMapping("/user")
    public UserDto addUser(@ApiParam(value = "新增用户参数", required = true) @RequestBody AddUserParam param) {
        logger.info("HelloController addUser : {}", param);
        UserDto userDto = new UserDto();
        userDto.setUsername(param.getName());
        return userDto;
    }
}
