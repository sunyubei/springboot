package com.nynu.springboot.conf;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 王纪勇
 * @Date: 2022/4/18 15:19
 * @Description: 配置nacos服务端, 开启客户端自动发现
 */

@EnableDiscoveryClient
@Configuration
public class NacosDiscoveryConfiguration {
}
