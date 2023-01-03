package com.nynu.springboot;

import com.nynu.springboot.service.UserService;
import com.nynu.springboot.utils.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {

        String username = "admin";
        String password = "123456";

        String token = TokenUtil.creatToken(username, password);
        System.out.println(token);

    }

    @Test
    public void Test() throws Exception {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsImV4cCI6MTY0OTIyNzI4NSwidXNlcm5hbWUiOiJhZG1pbiJ9.8uheJY_7tkhIdtj3K9g26DhcOfgWpWVBXRtZnQexDjo";
        boolean b = TokenUtil.checkToken(token);
        System.out.println(b);
    }

    @Test
    public void Test1() {
        String password = "123456";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(password);
        System.out.println(encode);
        boolean matches = encoder.matches(password, encode);
        System.out.println(matches);

    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void Test2() {

        String testone = ReadPropertiesUtil.getPropertieByName("testone");
        System.out.println(testone);

    }

    @Test
    public void Test3() {
        String password = "123456";
        String encode = new BCryptPasswordEncoder().encode(password);
        System.out.println(encode);
        boolean matches = new BCryptPasswordEncoder().matches(password, encode);
        System.out.printf("密码校验: " + matches);
    }


    {
        //先随机生产盐
        String salt = "$2a$10$Ztv/FcEu8We04HTUTcz2Qe";

        //执行hashpw方法
        //1：生产real_salt
        String real_salt = "Ztv/FcEu8We04HTUTcz2Qe";

        //2:生产密文
        String encode = "$2a$10$Ztv/FcEu8We04HTUTcz2QeAPEUcAL.rj22bz9NJPDdmSiMeOItQhq";

        //验证
        //1：传入salt（密文）
        String salts = "$2a$10$Ztv/FcEu8We04HTUTcz2QeAPEUcAL.rj22bz9NJPDdmSiMeOItQhq";
    }


    @Autowired
    private UserService userService;

    @Test
    @Scheduled(cron = "0 0/1 * * * ?")
    @Transactional
    public void ThreadTest() {

        System.err.println("流程开始");

        userService.executerMethod1();

        userService.executerMethod2();

        userService.executerMethod3();

        System.err.println("流程结束");

    }

    @Test
    public void Test5(){

        Date date = new Date();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));


    }

    public static long getStringToDate(String time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }





}