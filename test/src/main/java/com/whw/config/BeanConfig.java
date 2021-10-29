package com.whw.config;

import com.chiway.bean.MessageServiceProperties;
import com.chiway.service.SendMessage;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/11/11
 */
@EnableConfigurationProperties({MessageServiceProperties.class})
@Configuration
public class BeanConfig {

    @Bean
    public SendMessage sendMessage(MessageServiceProperties messageServiceProperties) {
        return new SendMessage(messageServiceProperties);
    }
}
