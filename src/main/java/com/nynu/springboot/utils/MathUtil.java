package com.nynu.springboot.utils;

import java.util.Random;

public class MathUtil {

    /**
     * @Author: 王纪勇
     * @Date: 2022/4/6 17:45
     * @Description: 生产一个长度为20的随机字符串的主键
     */
    public static String createId() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuffer stringBuffer = new StringBuffer(20);
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            stringBuffer.append(c);
        }
        return stringBuffer.toString();
    }

}
