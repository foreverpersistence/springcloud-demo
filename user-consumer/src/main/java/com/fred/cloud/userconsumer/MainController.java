package com.fred.cloud.userconsumer;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/getById")
    public String getById(/*@RequestParam */Integer id) {
        System.out.println("id = " + id);
        return userApi.getById(id);
    }

    @GetMapping("/getMap")
    public Map getMap(@RequestParam Map<String,Object> map) {

//        HashMap<String,Object> map1 = new HashMap<>(3);
//        map.put("id",2000);
//        map.put("name","fred");
//        map.put("age", 20);

        return userApi.getMap(map);
    }

    @GetMapping("/postUser")
    User post(@RequestParam Map<String,Object> map) throws InvocationTargetException, IllegalAccessException {

        User user = new User();
        BeanUtils.copyProperties(user, map);
        return userApi.post(user);
    }

}
