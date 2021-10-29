package com.whw.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2021/10/12
 */
@Component
public class ScheduleTaskService {

    private Map<String, ScheduledFuture<?>> futureMap;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @PostConstruct
    public void init() {
        futureMap = new ConcurrentHashMap<>();
    }


    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(8);
        return threadPoolTaskScheduler;
    }


    public boolean addTask(Runnable task, Trigger trigger, String key){
        ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(task, trigger);
        ScheduledFuture<?> put = futureMap.put(key, schedule);
        if (put == null) {
            return true;
        } else {
            throw new RuntimeException("添加任务key：" + key + " 重复");
        }
    }

    public boolean removeTask(String key) {
        ScheduledFuture<?> remove = futureMap.remove(key);
        if (remove != null) {
            remove.cancel(true);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateTask(Runnable task, Trigger trigger, String key) {
        ScheduledFuture<?> remove = futureMap.remove(key);
        if (remove != null) {
            remove.cancel(true);
        }
        return addTask(task, trigger, key);
    }

}
