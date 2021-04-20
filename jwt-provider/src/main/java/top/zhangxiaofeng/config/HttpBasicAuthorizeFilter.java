package top.zhangxiaofeng.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import top.zhangxiaofeng.common.ResponseCode;
import top.zhangxiaofeng.common.ResponseData;
import top.zhangxiaofeng.utils.JWTUtils;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * API 调用权限控制
 */
public class HttpBasicAuthorizeFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(getClass());

    JWTUtils jwtUtils = JWTUtils.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("HttpBasicAuthorizeFilter init method");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("HttpBasicAuthorizeFilter doFilter method");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        String auth = httpRequest.getHeader("Authorization");

        String requestURI = httpRequest.getRequestURI();
        logger.info("HttpBasicAuthorizeFilter request url:{}", requestURI);

        // 验证 TOKEN
        if (!StringUtils.hasText(auth)) {
            PrintWriter print = httpResponse.getWriter();
            print.write(JSON.toJSONString(ResponseData.fail("非法请求【缺少 Authorization 信息 ", ResponseCode.NO_AUTH_CODE.getCode())));
            return;
        }
        JWTUtils.JWTResult jwt = jwtUtils.checkToken(auth);
        if (!jwt.isStatus()) {
            PrintWriter print = httpResponse.getWriter();
            print.write(JSON.toJSONString(ResponseData.fail(jwt.getMsg(), jwt.getCode())));
            return;
        }
        chain.doFilter(httpRequest, response);
    }

    @Override
    public void destroy() {
        logger.info("HttpBasicAuthorizeFilter destroy method");
    }
}

