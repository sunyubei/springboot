package com.nynu.springboot.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 高德API工具类
 */
public class GaodeAPIUtil {

    //我的高德API密钥
    private static final String KEY = "68105ab91902be6c2fbb8a55db0d2709";

    /**
     * 批量地址转经纬度（一次支持1000条）
     */
    public void readAndChange(){

        String file = "C:/Users/wangj/Desktop/数据转换.txt";
        List<String> lists = testString(new File(file));
        List<List<String>> groupList = groupList(lists);
        groupList.forEach(list->{
            list.forEach(r->{
                String[] split = r.split("/");
                System.out.println(split[0] + " " + getLonAndLat(split[1], KEY));
            });
            try {
                //因个人免费账号每天的调用上限是5000次，并发量是30次/秒，所以每执行一次要强制休眠1秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * 单个地址转经纬度
     */
    public void addressChange(){
        try {

            Map<String, String> lonAndLat = getLonAndLat("北京市朝阳区阜通东大街6号", "68105ab91902be6c2fbb8a55db0d2709");
            System.out.println(lonAndLat);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取文件(转换成list)
     */
    public static List<String> testString(File file) {
        try {
            List<String> text = new ArrayList<>();
            BufferedReader br = new BufferedReader(new java.io.FileReader(file));
            String s = null;
            //使用readLine方法，一次读一行
            while ((s = br.readLine()) != null) {
                text.add(s);
            }
            br.close();
            return text;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 地址转经纬度
     * @param address 地址
     * @param key     高德地图应用key
     * @return 经纬度
     */
    public static Map getLonAndLat(String address, String key) {

        String queryUrl = "http://restapi.amap.com/v3/geocode/geo?key=" + key + "&address=" + address;
        String queryResult = getResponse(queryUrl);
        Map<String, String> map = new HashMap<>();
        JSONObject obj = JSONObject.parseObject(queryResult);

        if (obj.get("status").toString().equals("1")) {
            JSONObject jobJSON = JSONObject.parseObject(obj.get("geocodes").toString().substring(1, obj.get("geocodes").toString().length() - 1));
            String location = jobJSON.get("location").toString();
            String[] lonAndLat = location.split(",");
            if (lonAndLat != null && lonAndLat.length == 2) {
                map.put("lng", lonAndLat[0]);
                map.put("lat", lonAndLat[1]);
            }
            return map;
        } else {
            throw new RuntimeException("地址转换经纬度失败，错误码：" + obj.get("infocode"));
        }

    }

    /**
     * 发送请求
     * @param serverUrl 请求地址
     */
    private static String getResponse(String serverUrl) {

        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(serverUrl);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();

    }

    /**
     * 将list以30条数据的粒度分组
     */
    public static <T> List<List<T>> groupList(List<T> list){
        //listSize为集合长度
        int listSize=list.size();
        //每次取30条
        int index=30;
        List<List<T>> newList = new ArrayList<List<T>>(listSize / 30);
        //用list存起来新的分组后数据
        for(int i = 0;i<list.size();i+=index){
            //作用为Index最后没有1000条数据，则剩余的条数newList中就装几条
            if(i+index>listSize){
                index=listSize-i;
            }
            //使用subList方法
            newList.add(list.subList(i,i+index));
        }
        return newList;
    }


}
