package top.zhangxiaofeng.scheduled;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.zhangxiaofeng.common.AuthQuery;
import top.zhangxiaofeng.common.ResponseData;
import top.zhangxiaofeng.controller.AuthRemoteClient;

/**
 * 定时刷新 token
 **/
@Component
public class TokenScheduledTask {
    private static Logger logger = LoggerFactory.getLogger(TokenScheduledTask.class);

    public final static long ONE_Minute = 60 * 1000 * 60 * 20;

    @Autowired
    private AuthRemoteClient authRemoteClient;

    /**
     * 刷新 Token
     */
    @Scheduled(fixedDelay = ONE_Minute)
    public void reloadApiToken() {
        String token = this.getToken();
        while (StringUtils.isBlank(token)) {
            try {
                Thread.sleep(1000);
                token = this.getToken();
            } catch (InterruptedException e) {
                logger.error("", e);
            }
        }
        logger.info("TokenScheduledTask token:{}", token);
        System.setProperty("fangjia.auth.token", token);
    }

    public String getToken() {
        AuthQuery query = new AuthQuery();
        query.setAccessKey("username");
        query.setSecretKey("password");
        ResponseData response = authRemoteClient.auth(query);
        logger.info("TokenScheduledTask authRemoteClient response:{}", response);
        return response.getData() == null ? "" : response.getData().toString();
    }
}