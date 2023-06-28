package com.example.demo.controller.webSocket.pojo;

import com.example.demo.controller.webSocket.pojo.Enum.ControllerType;
import com.example.demo.controller.webSocket.pojo.Enum.MessageType;
import lombok.Data;

import javax.websocket.Session;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class Rooms {

    // RoomID : Room
    protected static final Map<String, Room> rooms = new ConcurrentHashMap<>();



    public static boolean joinRoom(String RoomId, String userId, Session session){

        if (rooms.containsKey(RoomId)){
            rooms.get(RoomId).joinRoom(userId, session);
            return true;
        }else {
            return false;
        }

    }

    public static void quitRoom(String RoomId, String userId){

        rooms.get(RoomId).quitRoom(userId);

    }

    public static boolean contains(String RoomId){

        return rooms.containsKey(RoomId);

    }

    public static int size(){
        return rooms.size();
    }

    private static HashSet<String> creatorSet = new HashSet<>();

    public static boolean containsCreator(String creatorId){

        return creatorSet.contains(creatorId);

    }

    public static boolean createRoom(String roomId, String creatorId){

        if (rooms.containsKey(roomId)){
            return false;
        }else {
            rooms.put(roomId, new Room(roomId, creatorId));
            creatorSet.add(creatorId);
            return true;
        }


    }


    public static boolean removeRoom(String roomId, String creatorId){

        if (rooms.get(roomId).getCreatorId().equals(creatorId)){

            Message message = new Message(MessageType.controllerMessage, "房间已被解散");
            message.setControllerType(ControllerType.disband);
            message.sendAllMessage(roomId);
            rooms.remove(roomId);
            creatorSet.remove(creatorId);
            return true;
        }
        return false;

    }

    public static Room getRoom(String RoomId){

        return rooms.get(RoomId);

    }

}
