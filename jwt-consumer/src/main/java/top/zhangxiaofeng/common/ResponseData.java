package top.zhangxiaofeng.common;

public class ResponseData {

    private String msg;
    private int code;
    private Object data;

    public ResponseData() {
    }

    public ResponseData(String msg, int code, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public static ResponseData failByParam(String accessKey_and_secretKey_not_null) {
        return new ResponseData(accessKey_and_secretKey_not_null, ResponseCode.FAIL_PARAM.getCode(), null);
    }

    public static ResponseData ok(String data) {
        return new ResponseData(null, ResponseCode.SUCCESS_CODE.getCode(), data);
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResponseData fail(String msg, int code) {
        return new ResponseData(msg, code, null);
    }


}