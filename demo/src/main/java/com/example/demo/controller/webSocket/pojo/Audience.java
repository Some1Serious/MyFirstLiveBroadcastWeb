package com.example.demo.controller.webSocket.pojo;

import com.example.demo.controller.webSocket.pojo.Enum.ControllerType;
import com.example.demo.controller.webSocket.pojo.Enum.MessageType;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@Slf4j
@ServerEndpoint("/audience/{roomId}/{userId}")
public class Audience {

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId, @PathParam(value = "roomId") String roomId) {
        try {

            if (Rooms.joinRoom(roomId, userId, session)){

                Message message = new Message(MessageType.controllerMessage, "加入房间成功!");
                message.setControllerType(ControllerType.success);

                if (session.isOpen()){
                    session.getAsyncRemote().sendText(message.toString());
                }
            }else {

                Message message = new Message(MessageType.controllerMessage, "加入房间失败!房间不存在");
                message.setControllerType(ControllerType.conflict);
                session.getAsyncRemote().sendText(message.toString());
                session.close();
            }


            log.info("【WebSocket消息】房间总数为：" + Rooms.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam(value = "userId") String userId, @PathParam(value = "roomId") String roomId) {
        try {

            Rooms.quitRoom(roomId, userId);
            log.info("【WebSocket消息】房间"+ roomId +"有用户断开，之后用户总数为：" + Rooms.rooms.get(roomId).size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, @PathParam(value = "roomId") String roomId) {
        log.info("【WebSocket消息】收到房间("+roomId+")消息：" + message);

        Message.sendAllMessage(message, roomId);
//        JSONObject obj = JSONUtil.parseObj(message);


    }



}
