package com.nynu.springboot.schedule;

import com.nynu.springboot.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Configuration
public class UserSchedule {

    @Resource
    private UserService userService;

    /**
     * 定时任务：一分钟执行一次
     */
//    @Scheduled(cron = "0 0/1 * * * ?")
    @Transactional
    public void ThreadTest() {

        System.err.println("流程开始");

        userService.executerMethod1();

        userService.executerMethod2();

        userService.executerMethod3();

        System.err.println("流程结束");

    }

}
