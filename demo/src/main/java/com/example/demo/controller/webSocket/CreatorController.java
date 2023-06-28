package com.example.demo.controller.webSocket;


import com.alibaba.fastjson.JSON;
import com.example.demo.controller.webSocket.pojo.Audience;
import com.example.demo.controller.webSocket.pojo.Enum.ControllerType;
import com.example.demo.controller.webSocket.pojo.Enum.MessageType;
import com.example.demo.controller.webSocket.pojo.Message;
import com.example.demo.controller.webSocket.pojo.Rooms;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Component
@Slf4j
@ServerEndpoint("/createRoom/{roomId}/{userId}")
public class CreatorController extends Audience {

    @Override
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId, @PathParam("roomId") String roomId) {


        if (!Rooms.createRoom(roomId, userId)){
            Message message = new Message(MessageType.controllerMessage, "房间以存在");
            message.setControllerType(ControllerType.conflict);
            session.getAsyncRemote().sendText(message.toString());

            try {
                session.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {

            Message message = new Message(MessageType.controllerMessage, "房间创建成功!");
            message.setControllerType(ControllerType.success);
            session.getAsyncRemote().sendText(message.toString());

            super.onOpen(session, userId, roomId);
        }





    }

    @Override
    @OnClose
    public void onClose(Session session,@PathParam("userId") String userId,@PathParam("roomId") String roomId) {

        super.onClose(session, userId, roomId);

        // 这里会有问题, 房间创建者不一定是房间的主人, 因为有可能创建者创建了别人已经创建的房间, 结果就是房间创建者被踢掉

//        Rooms.removeRoom(roomId, userId);

    }

}
