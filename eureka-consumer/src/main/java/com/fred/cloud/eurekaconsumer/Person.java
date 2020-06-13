package com.fred.cloud.eurekaconsumer;

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

    /**
     * 必须要 无参构造器，进行 对象 构造
     */
    public Person() {
    }

    /**
     * 没有  get/set 方法，getObj 输出解析不了
     * @return
     */
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
