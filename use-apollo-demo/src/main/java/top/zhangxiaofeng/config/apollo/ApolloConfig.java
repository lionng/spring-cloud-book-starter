package top.zhangxiaofeng.config.apollo;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApolloConfig {

    Logger logger = LoggerFactory.getLogger(getClass());

    @ApolloConfigChangeListener
    private void someOnChange(ConfigChangeEvent changeEvent) {
        if (changeEvent.isChanged("username")) {
            logger.warn("username发生修改了");
        }
    }
}
