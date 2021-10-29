package com.whw.config;

import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 此时输出的事件顺序如下：
 *
 * ApplicationListener#ApplicationStartingEvent
 * ApplicationListener#ApplicationEnvironmentPreparedEvent
 *   .   ____          _            __ _ _
 *  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
 * ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 *  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
 *   '  |____| .__|_| |_|_| |_\__, | / / / /
 *  =========|_|==============|___/=/_/_/_/
 *  :: Spring Boot ::        (v2.1.1.RELEASE)
 * 1
 * 2
 * 3
 * 4
 * 5
 * 6
 * 7
 * ApplicationListener#ApplicationContextInitializedEvent
 * ApplicationListener#ApplicationPreparedEvent
 * ApplicationListener#ContextRefreshedEvent 这里
 * ApplicationListener#ServletWebServerInitializedEvent
 * ApplicationListener#ApplicationStartedEvent
 * ApplicationListener#ApplicationReadyEvent
 * ApplicationListener#ContextClosedEvent
 * 差异
 * 很容易通过对比发现，Event 触发的时间极早，以至于 @Component 方式只能从第 4 个事件才开始获取到。
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2021/2/22
 */
@Component
public class ApplicationContextInitializedListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("ApplicationContextInitializedEvent listener....");
    }
}
