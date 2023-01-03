package com.nynu.springboot.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Author: 王纪勇
 * @Date: 2022/4/7 16:43
 * @Description: 读取服务的配置
 */
public class ReadPropertiesUtil {

    private static Properties properties;

    public static String getPropertieByName(String name) {
        initproperties();
        return properties.getOrDefault(name, "").toString();
    }

    private static Properties initproperties() {
        if (properties == null) {
            properties = new Properties();
            InputStreamReader inputStream = null;
            try {
                inputStream = new InputStreamReader(ReadPropertiesUtil.class.getClassLoader().getResourceAsStream("configure/test.properties"), "utf-8");
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

}
