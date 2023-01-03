package com.nynu.springboot.event;

import com.nynu.springboot.entity.Event;
import com.nynu.springboot.entity.User;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听器-基于接口
 */
@Component
public class MyInterfaceEventListener implements ApplicationListener<Event> {


    @Override
    public void onApplicationEvent(Event event) {
        //获取到事件
        User user = event.getUser();
        System.out.println("监听到" + user.getUserName());
        System.out.println("开始处理" + user.getUserName() + "的逻辑");
    }

}
