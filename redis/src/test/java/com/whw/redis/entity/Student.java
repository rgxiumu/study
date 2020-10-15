package com.whw.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 学生信息
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/9/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student implements Serializable {

    private Integer studentId;
    private String studentName;
    private String studentNo;
    private String studentPhone;

}
