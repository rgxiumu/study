package com.whw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/11/11
 */

public class App {

    public static void main(String[] args) {


        SpringApplication.run(Test2.class, args);


    }


}

@SpringBootApplication
@EnableAsync
class Test2 {

}