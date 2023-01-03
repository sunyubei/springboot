package com.nynu.springboot.service;

import com.nynu.springboot.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    //要注册的用户是否已经存在
    boolean checkUser(User user);

    void register(User user);

    boolean login(User user);

    User getUser(String userName);

    String getAndSetToken(User user);

    List<User> getPageList(User user);

    int addUser(User user);

    void executerMethod1();

    void executerMethod2();

    void executerMethod3();

}
