package top.zhangxiaofeng.config;

import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EurekaStateChangeListener {

    Logger logger = LoggerFactory.getLogger(getClass());

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        logger.warn(event.getServerId() + "\t" + event.getAppName() + " 服务下线 ");
        //System.err.println(event.getServerId() + "\t" + event.getAppName() + " 服务下线 ");
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        logger.info(instanceInfo.getAppName() + " 进行注册 ");
        //System.err.println(instanceInfo.getAppName() + " 进行注册 ");
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        logger.warn(event.getServerId() + "\t" + event.getAppName() + " 服务进行续约 ");
        //System.err.println(event.getServerId() + "\t" + event.getAppName() + " 服务进行续约 ");
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        logger.info(" 注册中心 启动 ");
        //System.err.println(" 注册中心 启动 ");
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        logger.info(" Eureka Server 启 动 ");
        //System.err.println(" Eureka Server 启 动 ");
    }
}