package com.nynu.springboot.service.serviceImpl;

import com.nynu.springboot.entity.Event;
import com.nynu.springboot.entity.User;
import com.nynu.springboot.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public User handleUser() {
        User user = new User("100","reed@ustc.com","nicai");
        Event event = new Event(this,user);
        applicationContext.publishEvent(event);
        return user;
    }

}
