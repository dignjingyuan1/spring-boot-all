package com.flow.snow.snow.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask {
    private int count=0;
    @Scheduled(cron="*/10 * * * * ?")
    private void process(){
        System.out.println("我的测试定时器：  "+(count++));
    }
}
