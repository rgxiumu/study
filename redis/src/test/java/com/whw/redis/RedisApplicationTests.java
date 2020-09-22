package com.whw.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@SpringBootTest
class RedisApplicationTests {

    private final static String IP = "192.168.145.10";
    private final static int PORT = 6379;
    private final static String PWD = "123456";

    @Test
    void testConnection() {
        Jedis jedis = new Jedis(IP, PORT);
        String ping = jedis.ping();
        System.out.println(ping);
    }

    @Test
    void testLettuce() {
        RedisURI redisURI = RedisURI.builder().withHost(IP).withPort(PORT).withPassword(PWD)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        RedisClient redisClient = RedisClient.create(redisURI);
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        RedisCommands<String, String> redisCommands  = connect.sync();
        SetArgs setArgs = SetArgs.Builder.nx().ex(5);
        String result = redisCommands.set("name", "throwable", setArgs);
        Assertions.assertThat(result).isEqualToIgnoringCase("OK");
        result = redisCommands.get("name");
        Assertions.assertThat(result).isEqualTo("throwable");
        connect.close();   // <5> 关闭连接
        redisClient.shutdown();  // <6> 关闭客户端
    }

}
