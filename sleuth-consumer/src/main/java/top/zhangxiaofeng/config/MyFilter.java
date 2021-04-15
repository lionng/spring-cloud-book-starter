package top.zhangxiaofeng.config;

import brave.Span;
import brave.Tracer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TracingFilter
 * 是负责处理请求和响应的组件，我们可以通过注册自定义的 TracingFilter 实例来实现一些扩展性的需求。
 * 下面给大家演示下如何给请求添加自定义的标记以及将请求 ID 添加到响应头返回给客户端。代码如下所示。
 */
@Component
@Order(5)
class MyFilter extends GenericFilterBean {

    private final Tracer tracer;

    MyFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Span currentSpan = this.tracer.currentSpan();
        if (currentSpan == null) {
            chain.doFilter(request, response);
            return;
        }
        ((HttpServletResponse) response).addHeader("ZIPKIN-TRACE-ID", currentSpan.context().traceIdString());
        currentSpan.tag("custom", "tag");
        chain.doFilter(request, response);
    }

}

