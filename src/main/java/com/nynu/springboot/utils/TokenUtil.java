package com.nynu.springboot.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;

/**
 * @Author: 王纪勇
 * @Date: 2022/4/6 11:22
 * @Description: Token工具类
 */
@Slf4j
public class TokenUtil {

    //token密钥
    private static final String TOKEN_SECRET = "wangjiyong";

    //过期时间(3小时)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 3;


    public String invokeTest(String parms){
        return parms + "hahahaha";
    }


    /**
     * @Author: 王纪勇
     * @Date: 2022/4/6 11:49
     * @Description: 生成token
     */
    public static String creatToken(String username, String password) {

        String token = "";

        try {
            //设置过期时间：当前时间+过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
            //设置签名加密算法：HMAC256
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置token头信息
            HashMap<String, Object> header = new HashMap<>();
            header.put("type", "JWT");
            header.put("algorithm", "HMAC256");
            //携带信息生成token(header, username, password, date, algorithm)
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("username", username)
                    .withClaim("password", password)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("token生成错误");
            e.printStackTrace();
        }

        return token;

    }

    /**
     * @Author: 王纪勇
     * @Date: 2022/4/6 14:10
     * @Description: 验证token
     */
    public static boolean checkToken(String token) throws Exception {

        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            jwtVerifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("token已过期");
            e.printStackTrace();
            return false;
        }

    }

}
