package com.fred.cloud.userconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author fred
 * @date 2020/5/26 8:05 下午
 * @description 2、定义  接口
 *
 * name 可以写成 服务名。不用写 url
 */
@FeignClient(name = "xx", url = "http://localhost:81")
public interface UserApi {

    @GetMapping("/alive")
    public String alive();

    @GetMapping("/registry")
    public String registry();

}
