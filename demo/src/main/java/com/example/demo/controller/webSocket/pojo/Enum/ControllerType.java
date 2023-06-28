package com.example.demo.controller.webSocket.pojo.Enum;

public enum ControllerType {

    conflict(1, "conflict"),
    success(2, "success"),

    disband(3, "disband");

    private final Integer type;
    private final String name;


    ControllerType(Integer type, String name) {
        this.type = type;
        this.name = name;
    }


    @Override
    public String toString() {
        return Integer.toString(type);
    }
}
