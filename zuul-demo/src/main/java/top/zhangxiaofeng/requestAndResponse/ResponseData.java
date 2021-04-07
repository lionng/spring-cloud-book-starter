package top.zhangxiaofeng.requestAndResponse;

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
