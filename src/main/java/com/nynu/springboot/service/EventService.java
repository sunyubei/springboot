package com.nynu.springboot.service;

import com.nynu.springboot.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface EventService {

    User handleUser();

}
