package com.whw.controller;

import com.whw.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2021/10/8
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("/test")
    public int test(){
        System.out.println(Thread.currentThread().getName());
        studentService.createStudents(3, "aaa");
        return 111;
    }
}
