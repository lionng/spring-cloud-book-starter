package top.zhangxiaofeng.config;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.zhangxiaofeng.requestAndResponse.ResponseCode;
import top.zhangxiaofeng.requestAndResponse.ResponseData;
import top.zhangxiaofeng.utils.IpUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 可以在此类上加上@Component,不配置FilterConfig配置类
 */
public class IpFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(getClass());

    // IP黑名单列表 0:0:0:0:0:0:0:1是本机ipv6地址，谷歌浏览器、postman请求是；192.168.50.166是本机ipv4地址，火狐浏览器请求是
    private List<String> blackIpList = Arrays.asList("0:0:0:0:0:0:0:1", "192.168.50.166");

    public IpFilter() {
        super();
    }

    /**
     * 过滤器类型，可选值有 pre、route、post、error。
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序，数值越小，优先级越高。
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否执行该过滤器，true 为执行，false 为不执行，这个也可以利用配置中心来实现，达到动态的开启和关闭过滤器。
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 执行自己的业务逻辑，本段代码中是通过判断请求的 IP 是否在黑名单中，决定是否进行拦截。blackIpList 字段是 IP 的黑名单，判断条件成立之后，通过设置 ctx.setSendZuulResponse（false），告诉 Zuul 不需要将当前请求转发到后端的服务了。通过 setResponseBody 返回数据给客户端。
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        String ip = IpUtils.getIpAddr(ctx.getRequest());
        logger.info("IpFilter ip:{}", ip);
        // 在黑名单中禁用
        if (StringUtils.isNotBlank(ip) && blackIpList.contains(ip)) {
            logger.error("IpFilter black ip:{}", ip);
            ctx.setSendZuulResponse(false);
            ResponseData data = ResponseData.fail("非法请求 ", ResponseCode.NO_AUTH_CODE.getCode());
            ctx.setResponseBody(JSON.toJSONString(data));
            ctx.getResponse().setContentType("application/json; charset=utf-8");
            return null;
        }
        return null;
    }
}
