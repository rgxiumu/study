package com.whw.core;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2021/10/11
 */
@ServerEndpoint("/websocket/{id}")
@Component
public class WebsocketEndPoint {


    private static AtomicInteger onlineCount = new AtomicInteger(0);

    private static CopyOnWriteArraySet<WebsocketEndPoint> websocketSet = new CopyOnWriteArraySet<>();

    private Session session;

    private String id = "";

    private static ScheduleTaskService scheduleTaskService;

    @Autowired
    public void setScheduleTaskService(ScheduleTaskService scheduleTaskService) {
        WebsocketEndPoint.scheduleTaskService = scheduleTaskService;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        this.id = id;
        this.session = session;
        websocketSet.add(this);
        addOnlineCount();
        boolean b = scheduleTaskService.addTask(new Runnable() {
            int count = 0;
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("test" + count++);
                sendMessage("test" + count++);
                if(count > 100) {
                    onClose();
                }
            }
        }, new CronTrigger("0/1 * * * * ? "), id);
    }

    @OnClose
    public void onClose() {
        scheduleTaskService.removeTask(this.id);
        websocketSet.remove(this);
        subOnlineCount();
    }


    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
//        for (WebsocketEndPoint websocketEndPoint : websocketSet) {
//            websocketEndPoint.sendMessage(message);
//        }
    }

    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized void addOnlineCount(){
        WebsocketEndPoint.onlineCount.set(getOnlineCount() + 1);
    }

    public static synchronized Integer getOnlineCount(){
        return Integer.valueOf(onlineCount.toString());
    }

    public static synchronized void subOnlineCount(){
        WebsocketEndPoint.onlineCount.set(getOnlineCount() - 1);
    }


    public static CopyOnWriteArraySet<WebsocketEndPoint> getWebsocketSet() {
        return websocketSet;
    }

    public String getId(){
        return id;
    }

}
