package com.example.demo.pojo.Enum;

public enum Role {
    student(1, "student"),
    teacher(2, "teacher"),
    administrator(3, "administrator");

    private final Integer role;
    private final String name;


    Role(Integer role, String name) {
        this.role = role;
        this.name = name;
    }
}
