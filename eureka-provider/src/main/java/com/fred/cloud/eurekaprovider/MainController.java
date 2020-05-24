package com.fred.cloud.eurekaprovider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fred
 * @date 2020/5/24 6:53 下午
 * @description todo
 */
@RestController
public class MainController {

    @GetMapping("/getHi")
    public String getHi() {
        return "hello";
    }

}
