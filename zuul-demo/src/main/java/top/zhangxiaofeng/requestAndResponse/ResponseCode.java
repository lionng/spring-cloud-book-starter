package top.zhangxiaofeng.requestAndResponse;

public enum ResponseCode {
    NO_AUTH_CODE(1, "非法请求");

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
