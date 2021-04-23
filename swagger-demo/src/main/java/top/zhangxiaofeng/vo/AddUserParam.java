package top.zhangxiaofeng.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "top.zhangxiaofeng.vo.AddUserParam", description = "新增用户参数")
public class AddUserParam {
    @ApiModelProperty(value = "ID")
    private String id;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "年龄")
    private int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}