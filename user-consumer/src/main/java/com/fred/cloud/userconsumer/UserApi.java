package com.fred.cloud.userconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author fred
 * @date 2020/5/26 8:05 下午
 * @description 2、定义  接口
 *
 * name 可以写成 服务名。不用写 url
 */
@FeignClient(name = "user-provider") // 直接 服务名，不用写 url
//@FeignClient(name = "xx", url = "http://localhost:71")
public interface UserApi {

    @GetMapping("/alive")
    public String alive();

    @GetMapping("/registry")
    public String registry();

    /**
     * 带参数，
     *  所有的 web 注解都是给 feign 看的。需要 添加有所的相关的 注解RequestParam
     *  组装 url 使用/
     *
     *
     *
     *  Resolved [org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'POST' not supported]
     *  需要添加 feign-httpclient
     * @param id
     * @return
     */
    @GetMapping("getById")
    public String getById(@RequestParam("id") Integer id);

    /**
     * map
     * @param map
     * @return
     */
    @GetMapping("/getMap")
    Map getMap(@RequestParam Map<String,Object> map);


    @PostMapping("/postUser")
    User post(@RequestBody User user);

}
