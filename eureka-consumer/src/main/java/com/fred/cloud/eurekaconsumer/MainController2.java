package com.fred.cloud.eurekaconsumer;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author fred
 * @date 2020/5/24 6:53 下午
 * @description 服务发现
 */
@RestController
public class MainController2 {

    @Autowired
    DiscoveryClient client;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    /**
     * REST API
     *  资源 + 方法名
     *  http://xxx/User/getUserList    GET
     *  http://xxx/users               GET 约定
     *  http://xxx/v1/User/getUserList    GET
     *
     *  http://xxx/User/deleteById     GET、POST
     *  http://xxx/v1/User/deleteById     GET、POST
     *
     *  http://xxx/users/1              GET 获取ID=1的资源  DELETE 删除
     *  http://xxx/v1/users/1
     *
     *  针对单表， 不再重复 crud         SpringDATA Rest
     *
     */
    @GetMapping("/client6")
    public Object client6() {

        System.out.println("====user  ribbon ========");


        ServiceInstance instance = loadBalancerClient.choose("provider");

        // url   拼接
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getHi";
        System.out.println("url: " + url);

        String response = restTemplate.getForObject(url, String.class);
        System.out.println("ribbon response:" + response);
        return "ribbon info";
    }

    @GetMapping("/client9")
    public Object client9() {
        // 服务主机名 + 服务 资源名, ribbon 参与
        String url = "http://provider/getHi";
        String resp = restTemplate.getForObject(url, String.class);
        System.out.println("client 9  resp:" + resp);
        return resp;
    }



    /**
     * ribbon  使用
     * @return
     */
    @GetMapping("/client7")
    public Object ribbon() {

        System.out.println("====user  ribbon ========");

        List<ServiceInstance> instances = client.getInstances("provider");

//        ServiceInstance provider = balancerClient.choose("provider");

        ServiceInstance instance = instances.get(0);
        // url   拼接
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getHi";
        System.out.println("url: " + url);


        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        System.out.println("ribbon response:" + response);
        return "ribbon info";
    }
}
