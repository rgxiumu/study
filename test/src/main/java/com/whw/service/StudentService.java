package com.whw.service;

import com.whw.aop.LogRecord;
import com.whw.core.aop.LogRecordContext;
import com.whw.entity.StudentTrans;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2021/9/29
 */
@Service
public class StudentService {



//    @LogRecord(operation = "新增学生信息，学校#{#schoolId}, 学号#{#studentNo}, 修改成功数#{#successNo}", clazz = StudentTrans.class)
    @Async
    public void createStudents(int schoolId, String studentNo){
//        LogRecordContext.setValue("successNo", 100);


        System.out.println(Thread.currentThread().getName());
    }
}
