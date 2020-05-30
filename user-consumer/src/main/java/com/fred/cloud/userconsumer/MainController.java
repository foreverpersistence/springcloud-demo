package com.fred.cloud.userconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fred
 * @date 2020/5/26 8:08 下午
 * @description feign 调用
 */
@RestController
public class MainController {

    @Autowired
    UserApi userApi;

    @GetMapping("/alive")
    public String alive() {
        return userApi.alive();
    }
}
