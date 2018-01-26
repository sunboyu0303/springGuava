package com.example.spring.guava.domain;

import lombok.Data;

/**
 * @author sunboyu
 * @date 2018/1/26
 */
@Data
public class Person {

    private String id;
    private String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
