package com.fred.cloud.eurekaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EurekaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced// http://provider/getHi,  根据 服务 获取 调用 ip
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
