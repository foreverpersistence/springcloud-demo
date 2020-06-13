package com.fred.cloud.eurekaprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author fred
 * @date 2020/5/24 6:53 下午
 * @description todo
 */
@RestController
public class MainController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/getHi")
    public String getHi() {
        return "hello,my port is " + port;
    }

    @GetMapping("/getMap")
    public Map<String,String> getMap() {
        return Collections.singletonMap("id","100");
    }

    @GetMapping("/getObj")
    public Object getObj() {
        Person person = new Person(100, "fred");
        return person;
    }

    /**
     * 带 参数
     * @param name
     * @return
     */
    @GetMapping("/getObj2")
    public Object getObj(String name) {
        Person person = new Person(100, name);
        return person;
    }
}
