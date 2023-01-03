package com.nynu.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class Event extends ApplicationEvent {

    private User user;

    public Event(Object source, User user) {
        super(source);
        this.user = user;
    }

}
