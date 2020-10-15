package com.whw.redis;

import com.alibaba.fastjson.JSONObject;
import com.whw.redis.entity.Student;
import com.whw.redis.utils.RedisUtil;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Slf4j
class RedisApplicationTests {

    private final static String IP = "192.168.145.10";
    private final static int PORT = 6379;
    private final static String PWD = "123456";


    @Autowired
    private RedisUtil redisUtil;

    @Test
    void testConnection() {
        Jedis jedis = new Jedis(IP, PORT);
        jedis.auth(PWD);
        String ping = jedis.ping();
        System.out.println(ping);
        String name = jedis.get("name");
        System.out.println(name);
    }
    @Test
    void testConnection2() {
        Jedis jedis = new Jedis("10.5.96.62", PORT);
        jedis.auth(PWD);
        String ping = jedis.ping();
        System.out.println(ping);
        jedis.set("name", "test");
        String name = jedis.get("name");
        System.out.println(name);
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
        Assertions.assertEquals("throwable", result);
        result = redisCommands.get("name");
        Assertions.assertEquals("throwable", result);
        connect.close();   // <5> 关闭连接
        redisClient.shutdown();  // <6> 关闭客户端
    }

    @Test
    void testString() {
        redisUtil.set("tyrss3", "wuhw");
        Object author = redisUtil.get("author");
        log.info(author.toString());
    }

    @Test
    void testObject() {
        Student student = new Student(1, "兰奇", "889221", "18799922111");
        boolean fl = redisUtil.set("student", student);
        Assertions.assertTrue(fl);

        Student student1 = (Student)redisUtil.get("student");
        log.info(student1.toString());
    }


    @Test
    void testList() {
        redisUtil.del("allStudents");
        Student student = new Student(1, "兰奇", "889221", "18799922111");
        Student student2 = new Student(2, "十七", "112332", "18799922121");

        List<Student> students = Arrays.asList(student, student2);
        boolean fl = redisUtil.lSet("allStudents", students);
        Assertions.assertTrue(fl);
        List<Student> allStudents2 = (List<Student>) redisUtil.lGetIndex("allStudents", 0);
        log.info(allStudents2.toString());
    }
    @Test
    void testNullKey() {
        Object testnull = redisUtil.get("aa");
        System.out.println(testnull);

        List<Student> students = JSONObject.parseArray(String.valueOf(redisUtil.get("aa")), Student.class);
        System.out.println(students);

//
//        redisUtil.set("aa", JSONObject.toJSONString(Lists.newArrayList()), 24 * 60 * 60);
//        Object tt = redisUtil.get("testnull");
//        System.out.println(testnull);

    }
}
