package top.zhangxiaofeng.alert.dingding;

import com.alibaba.fastjson.JSON;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DingDingMessageUtil {
    /**
     * U2FsdGVkX1/9bj68CECeXcIPM7kpp1wqQC4eXCJGLrGRt2Y5G4Sw9XbpZf6cjGRE
     * fTBPIbhlyIAgHEF9n/u7CKNFpuVAEwMYgAth8ZSwhX8QnKjQES9Ri0HySnYfGrL1
     */
    public static String access_token = "25de13df3dea2f92432bf8527e15dc6c4648206b4d2fa81507f362b6875d6913";

    public static void sendTextMessage(String msg) {
        try {
            Message message = new Message();
            message.setMsgtype("text");
            message.setText(new MessageInfo(msg));
            URL url = new URL("https://oapi.dingtalk.com/robot/send?access_token=" + access_token);
            // 建立 http 连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/Json; charset=UTF-8");
            conn.connect();
            OutputStream out = conn.getOutputStream();
            String textMessage = JSON.toJSONString(message);
            byte[] data = textMessage.getBytes();
            out.write(data);
            out.flush();
            out.close();

            InputStream in = conn.getInputStream();
            byte[] data1 = new byte[in.available()];
            in.read(data1);

            System.out.println(new String(data1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class Message {
    private String msgtype;
    private MessageInfo text;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public MessageInfo getText() {
        return text;
    }

    public void setText(MessageInfo text) {
        this.text = text;
    }
}

class MessageInfo {
    private String content;

    public MessageInfo(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

