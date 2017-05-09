package com.sbm.schedule;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by lsx on 2017/4/18.
 */
@Component
public class AppCoreTask {
    @Scheduled(cron = "30 * * * * ?")
    public void test(){
        System.out.println("开启定时任务；"+new Date());
    }
}
