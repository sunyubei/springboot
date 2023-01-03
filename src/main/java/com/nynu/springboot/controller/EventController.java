package com.nynu.springboot.controller;

import com.nynu.springboot.entity.User;
import com.nynu.springboot.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public User monitor(){
        User user = eventService.handleUser();
        return user;
    }

}
