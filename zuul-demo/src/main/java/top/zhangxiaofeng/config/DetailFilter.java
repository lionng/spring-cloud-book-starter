package top.zhangxiaofeng.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.micrometer.core.instrument.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

/**
 * 在 Zuul 中输出请求响应的信息来辅助我们解决一些问题。
 */
@Component
public class DetailFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        HttpServletRequest req = RequestContext.getCurrentContext().getRequest();
        logger.info("-----------------------------start------------------------------------");
        logger.info("REQUEST:: {} {}:{}", req.getScheme(), req.getRemoteAddr(), req.getRemotePort());
        StringBuilder params = new StringBuilder("?");
        // 获取URL参数
        Enumeration<String> names = req.getParameterNames();
        if (req.getMethod().equals("GET")) {
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                params.append(name);
                params.append("=");
                params.append(req.getParameter(name));
                params.append("&");
            }
        }
        if (params.length() > 0) {
            params.delete(params.length() - 1, params.length());
        }
        logger.info("REQUEST:: > {} {}{} {}", req.getMethod(), req.getRequestURI(), params, req.getProtocol());
        Enumeration<String> headers = req.getHeaderNames();
        while (headers.hasMoreElements()) {
            String name = headers.nextElement();
            String value = req.getHeader(name);
            logger.info("REQUEST:: > {}:{}", name, value);
        }
        final RequestContext ctx = RequestContext.getCurrentContext();

        /**
         * Zuul自带的Debug功能
         */
        ctx.setDebugRouting(true);
        ctx.setDebugRequest(true);

        // 获取请求体参数
        if (!ctx.isChunkedRequestBody()) {
            ServletInputStream inp;
            try {
                inp = ctx.getRequest().getInputStream();
                String body;
                if (inp != null) {
                    body = IOUtils.toString(inp);
                    logger.info("REQUEST:: > {}", body);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 获取响应
        InputStream stream = RequestContext.getCurrentContext().getResponseDataStream();
        if (stream != null) {
            String body = IOUtils.toString(stream);
            logger.info("RESPONSE:: > {}", body);
            RequestContext.getCurrentContext().setResponseBody(body);
        }
        logger.info("-----------------------------end------------------------------------");
        return null;
    }
}
