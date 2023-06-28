package com.example.demo.controller.webSocket.pojo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.controller.webSocket.pojo.Enum.ControllerType;
import com.example.demo.controller.webSocket.pojo.Enum.MessageType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.Session;

@Data
@Slf4j
public class Message {

//    type
    private MessageType type;

    private String message;

    private String id;

    private String from;

    private ControllerType controllerType;

    public Message(MessageType type, String message) {
        this.type = type;
        this.message = message;
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this, SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.WriteNonStringValueAsString,SerializerFeature.WriteEnumUsingToString);
    }

    /**
     * 此为广播消息
     *
     *
     */
    public void sendAllMessage(String roomId) {
        log.info("【WebSocket消息】广播消息：" +  this);
        for (Session session : Rooms.rooms.get(roomId).getSessions()) {
            try {
                if (session.isOpen()) {
                    session.getAsyncRemote().sendText(this.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendAllMessage(String message, String roomId) {
        log.info("【WebSocket消息】广播消息：" + message);
        for (Session session : Rooms.rooms.get(roomId).getSessions()) {
            try {
                if (session.isOpen()) {
                    session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
