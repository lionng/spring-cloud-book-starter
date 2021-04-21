package top.zhangxiaofeng.common;

public enum ResponseCode {
    SUCCESS_CODE(2000, "成功"),
    SERVER_ERROR_CODE(1500, "服务错误"),
    NO_AUTH_CODE(1401, "权限校验失败"),
    TOKEN_TIMEOUT_CODE(1402, "token时间过期"),
    FAIL_PARAM(1600, "参数错误");

    ResponseCode() {
    }

    ResponseCode(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    private String msg;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}