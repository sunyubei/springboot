package com.nynu.springboot.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nynu.springboot.entity.User;
import com.nynu.springboot.service.UserService;
import com.nynu.springboot.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: 王纪勇
 * @Date: 2022/4/7 10:01
 * @Description: 用户基本功能接口
 */
@RestController
@RequestMapping("/user")
public class UserBaseController {

    @Autowired
    private UserService userService;

    /**
     * @Author: 王纪勇
     * @Date: 2022/4/7 10:01
     * @Description: 注册用户
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public R register(@RequestBody User user) {

        //判断要注册的用户是否已经存在
        boolean isExit = userService.checkUser(user);
        //如果已经存在返回false
        if (isExit == false) {
            return R.error(500, "该用户已存在，请更改用户名");
        }
        try {
            userService.register(user);
            return R.ok("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error("注册失败");

    }

    /**
     * @Author: 王纪勇
     * @Date: 2022/4/8 11:00
     * @Description: 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public R login(@RequestBody User user) {

        //登录验证
        boolean result = userService.login(user);
        //用户密码正确
        if (result) {
            //生产token并进行存储token(k:userName-v:user)
            String token = userService.getAndSetToken(user);
            HashMap<String, Object> map = new HashMap<>();
            map.put("登录成功", 200);
            map.put("token", token);
            return R.ok(map);
        }
        return R.error("登录失败");

    }

    /**
     * @Author: 王纪勇
     * @Date: 2022/4/8 10:54
     * @Description: 登录后的主页面
     */
    @RequestMapping(value = "/getMainMenu", method = RequestMethod.GET)
    public R getMainMenu() {
        System.out.println("1111");
        return R.ok("Welcome to menu");
    }

    /**
     * @Author: 王纪勇
     * @Date: 2022/4/8 14:31
     * @Description: 分页查询
     */
    @RequestMapping(value = "/getPageList", method = RequestMethod.POST)
    public Page<User> getPageList(@RequestBody User user){
//        PageHelper.startPage(user.getPage(), user.getLimit());
        List<User> list = userService.getPageList(user);
        Page<User> pageList = (Page<User>)list;
        return pageList;

    }


}
