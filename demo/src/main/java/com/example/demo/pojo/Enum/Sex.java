package com.example.demo.pojo.Enum;

import lombok.Getter;

@Getter
public enum Sex {

    MALE(1, "男"),
    FEMALE(2, "女"),
    UNKNOWN(3, "未知");

    private final Integer sex;
    private final String name;

    Sex(Integer sex, String name) {
        this.sex = sex;
        this.name = name;
    }
}
