package top.zhangxiaofeng.config;

import brave.http.HttpRequest;
import brave.sampler.SamplerFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.web.HttpServerSampler;
import org.springframework.cloud.sleuth.instrument.web.SkipPatternProvider;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * 过滤不想跟踪的请求
 * 对于某些请求不想开启跟踪，可以通过配置 HttpSampler 来过滤掉，比如 swagger 这些请求等
 */
@Component(value = HttpServerSampler.NAME)
public class MyHttpSampler implements SamplerFunction<HttpRequest> {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SkipPatternProvider provider;

    @Override
    public Boolean trySample(HttpRequest httpRequest) {
        Pattern pattern = provider.skipPattern();
        String url = httpRequest.url();
        String path = httpRequest.path();
        boolean shouldSkip = pattern.matcher(path).matches();
        if (shouldSkip) {
            logger.warn("MyHttpSampler skip url:{}", url);
            logger.warn("MyHttpSampler skip path:{}", path);
            return false;
        }
        return null;
    }
}