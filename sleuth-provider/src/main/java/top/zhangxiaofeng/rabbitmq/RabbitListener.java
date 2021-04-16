package top.zhangxiaofeng.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

/**
 * 监听下 RabbitMq
 */
//@Component
public class RabbitListener {

    Logger logger = LoggerFactory.getLogger(getClass());

    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = "zipkin")
    private void handleMessage(Message message) {
        String messageBody = new String(message.getBody());
        logger.info("RabbitListener queues:zipkin message:{}", message);
//        System.out.println("收到了消息是 : " + messageBody);
    }

}