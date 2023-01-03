package com.nynu.springboot;

import com.nynu.springboot.utils.GaodeAPIUtil;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

public class GaodeAPITest {

    //我的高德API密钥
    private static final String KEY = "68105ab91902be6c2fbb8a55db0d2709";

    /**
     * 单个地址转经纬度
     */
    @Test
    public void addressChange(){
        try {

            Map<String, String> lonAndLat = GaodeAPIUtil.getLonAndLat("北京市朝阳区阜通东大街6号", "68105ab91902be6c2fbb8a55db0d2709");
            System.out.println(lonAndLat);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 批量地址转经纬度（一次支持1000条）
     */
    @Test
    public void readAndChange(){

        String file = "C:/Users/wangj/Desktop/数据转换.txt";
        List<String> lists = GaodeAPIUtil.testString(new File(file));
        List<List<String>> groupList = GaodeAPIUtil.groupList(lists);
        groupList.forEach(list->{
            list.forEach(r->{
                String[] split = r.split("/");
                System.out.println(split[0] + " " + GaodeAPIUtil.getLonAndLat(split[1], KEY));
            });
            try {
                //因个人免费账号每天的调用上限是5000次，并发量是30次/秒，所以每执行一次要强制休眠2秒
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

}
