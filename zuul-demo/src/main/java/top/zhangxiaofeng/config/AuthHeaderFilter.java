package top.zhangxiaofeng.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 调用服务前添加认证请求头过滤器
 **/
@Component
public class AuthHeaderFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(getClass());

    public AuthHeaderFilter() {
        super();
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Object success = ctx.get("isSuccess");
        return success == null ? true : Boolean.parseBoolean(success.toString());
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String token = System.getProperty("fangjia.auth.token");
//        String token = "eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNjE5MDk3ODk5fQ.p-QJ5eibp5D3VdO53EM4V_h0J9udH8Fjo4LyASy3wOkjlMdjCAbaXiL8w9XmwoyhWEQFe4x95o7lmplkhQIRPrquJuRnw4tZBddGeHSZjwk6Lgq-1Xo9IQRG7b7MxqIfs865d946ho6TRu48IumUgdDIMpFgSvV1VkQtfySdM-I";
        if (token == null) {
            logger.warn("AuthHeaderFilter get token:null");
        } else {
            logger.info("AuthHeaderFilter get token:{}", token);
        }
        ctx.addZuulRequestHeader("Authorization", token);
        return null;
    }
}