package top.zhangxiaofeng.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 异步任务线程池定义
 *
 * Sleuth 对异步任务也是支持的，我们用 @Async 开启一个异步任务后，Sleuth 会为这个调用新创建一个 Span。
 *
 * 如果你自定义了异步任务的线程池，会导致无法新创建一个 Span，这就需要使用 Sleuth 提供的 LazyTraceExecutor 来包装下。代码如下所示。
 */
@Configuration
@EnableAutoConfiguration
public class CustomZipkinExecutorConfig extends AsyncConfigurerSupport {

    @Autowired
    BeanFactory beanFactory;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(7);
        executor.setMaxPoolSize(42);
        executor.setQueueCapacity(11);
        executor.setThreadNamePrefix("yinjihuan-");
        executor.initialize();
        return new LazyTraceExecutor(this.beanFactory, executor);
    }
}
