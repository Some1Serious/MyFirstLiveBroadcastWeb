package com.example.demo.controller.webSocket.pojo.Enum;

public enum MessageType {

    enter(1, "enter"),
    leave(2, "leave"),
    sendMessage(3, "sendMessage"),
    systemMessage(4, "systemMessage"),
    controllerMessage(5, "controllerMessage");

    private final Integer type;
    private final String name;


    MessageType(Integer type, String name) {
        this.type = type;
        this.name = name;
    }


    @Override
    public String toString() {
        return Integer.toString(type) ;
    }
}
