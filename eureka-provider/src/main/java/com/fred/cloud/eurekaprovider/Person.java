package com.fred.cloud.eurekaprovider;

/**
 * @author fred
 * @date 2020/5/25 2:31 下午
 * @description todo
 */
public class Person {

    private Integer id;
    private String name;

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
