package com.fred.cloud.eurekaconsumer;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sun.awt.geom.AreaOp;

import java.util.List;

/**
 * @author fred
 * @date 2020/5/24 6:53 下午
 * @description todo
 */
@RestController
public class MainController {

    @Autowired
    DiscoveryClient client;

    @Autowired
    EurekaClient eurekaClient;

    @Autowired
    LoadBalancerClient balancerClient;

    @GetMapping("/getHi")
    public String getHi() {
        return "hello";
    }



    @GetMapping("/client")
    public String getHi2() {

        List<String> services =
                client.getServices();
        services.stream().forEach(s -> System.out.println("service: " + s));
        return "client";
    }

    @GetMapping("/client2")
    public Object client2() {

        return client.getInstances("provider");

    }

    @GetMapping("/client3")
    public Object client3() {

        List<ServiceInstance> provider = client.getInstances("provider");

        provider.stream().forEach(serviceInstance ->
                System.out.println(ToStringBuilder.reflectionToString(serviceInstance)));

        return "instance info";

    }

    @GetMapping("/client4")
    public Object client4() {

//根据
        List instancesById = eurekaClient.getInstancesById("");
//根据 服务名 获取 列表
        List<InstanceInfo> instancesByVipAddress = eurekaClient.getInstancesByVipAddress("provider", false);

        if (instancesByVipAddress.size() > 0) {
            InstanceInfo instanceInfo = instancesByVipAddress.get(0);
            if (instanceInfo.getStatus() == InstanceInfo.InstanceStatus.UP) {
                // url   拼接
                String url = "http://" + instanceInfo.getHostName() + ":" + instanceInfo.getPort() + "/getHi";
                System.out.println("url: " + url);


                RestTemplate restTemplate = new RestTemplate();
                String response = restTemplate.getForObject(url, String.class);
                System.out.println("response:" + response);
            }
        }
        return "instance info";
    }

    /**
     * ribbon  使用
     * @return
     */
    @GetMapping("/ribbon")
    public Object ribbon() {

        System.out.println("====user  ribbon ========");


        ServiceInstance provider = balancerClient.choose("provider");

        // url   拼接
        String url = "http://" + provider.getHost() + ":" + provider.getPort() + "/getHi";
        System.out.println("url: " + url);


        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        System.out.println("ribbon response:" + response);
        return "ribbon info";
    }
}
