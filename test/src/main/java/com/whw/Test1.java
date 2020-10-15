package com.whw;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/8/14
 */
@Data
public class Test1 {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        Student s1 = new Test1().new Student("s1", "1");
        Student s2 = new Test1().new Student("s2", "2");
        Student s3 = new Test1().new Student("s3", "3");
        list.add(s1);
        list.add(s2);
        list.add(s3);
        long count = list.stream().map(Student::getIdNum).distinct().count();
        if (count != list.size()) {
            throw new RuntimeException("队列异常");
        }
        Map<String, Student> collect = list.stream().collect(Collectors.toMap(Student::getIdNum, s -> s));
        collect.forEach((k, v) -> {
            System.out.println(k + "----" + v);
        });
    }


    @Data
    @AllArgsConstructor
    class Student{
        private String name;
        private String idNum;
    }
}
