package com.fred.cloud.eurekaconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author fred
 * @date 2020/5/24 6:53 下午
 * @description 服务发现
 */
@RestController
public class MainController3 {

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

    @GetMapping("/client10")
    public Object client10() {
        // 服务主机名 + 服务 资源名
        String url = "http://provider/getHi";
        String resp = restTemplate.getForObject(url, String.class);

        //返回 如响应码、contentType、contentLength、响应消息体等 信息
//        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
//        System.out.println("client 10 entity:" + entity);

        System.out.println("client 10 resp:" + resp);
        return resp;
    }

    @GetMapping("/client11")
    public Object getMap() {
        // 服务主机名 + 服务 资源名
        String url = "http://provider/getMap";
        Map<String,String> resp = restTemplate.getForObject(url, Map.class);

        //返回 如响应码、contentType、contentLength、响应消息体等 信息
//        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
//        System.out.println("client 10 entity:" + entity);

        System.out.println("client 11 resp:" + resp);
        return resp;
    }


    @GetMapping("/client12")
    public Object getObj() {
        // 服务主机名 + 服务 资源名
        String url = "http://provider/getObj";
        Person per = restTemplate.getForObject(url, Person.class);

        System.out.println("client 12 resp:" + per);
        return per;
    }

    @GetMapping("/client13")
    public Object getObj2() {
        // 服务主机名 + 服务 资源名， 自动处理 url
        String url = "http://provider/getObj2?name={1}";
        Person per = restTemplate.getForObject(url, Person.class, "fred666");

        System.out.println("client 13 resp:" + per);
        return per;
    }


    /**
     * 使用 map 传参数
     * @return
     */
    @GetMapping("/client14")
    public Object getObjByMap() {
        // 服务主机名 + 服务 资源名， 自动处理 url
        String url = "http://provider/getObj2?name={name}";// 占位符 对应
        Map<String, String> map = Collections.singletonMap("name", "fred2020");
        Person per = restTemplate.getForObject(url, Person.class, map);

        System.out.println("client 13 resp:" + per);
        return per;
    }


    /**
     * POST
     * @return
     */
    @GetMapping("/client15")
    public Object getObjByPost() {
        // 服务主机名 + 服务 资源名， 自动处理 url
        String url = "http://provider/postObj";// 占位符 对应
        Map<String, String> map = Collections.singletonMap("name", "fred2020");
        Person per = restTemplate.postForObject(url,map, Person.class);

        System.out.println("client 13 resp:" + per);
        return per;
    }



}
