package com.fred.cloud.userprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fred
 * @date 2020/5/26 7:54 下午
 * @description Feign  调用
 */
@RestController
public class UserController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/alive")
    public String alive() {
        return "OK。port：" + port;
    }

    @GetMapping("/getById")
    public String getById(@RequestParam Integer id) {
        return "id=" + id;
    }

    /**
     * @RequestParam 必须添加，才能 获取到值
     * @param map
     * @return
     */
    @GetMapping("/getMap")
    public Map getMap(@RequestParam Map<String, Object> map) {

        String mapStr = map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        System.out.println(mapStr);
        return Collections.singletonMap("test","getMap");
    }

    @PostMapping("/postUser")
    public User getUser(@RequestBody User user) {
        System.out.println(user);

        return user;

    }

}
