package com.example.demo.controller.webSocket.pojo;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.Session;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class Room {

    private String roomId;
    private String creatorId;

    // userId : Session
    private Map<String, Session> SESSION_POOL;

    public Room(String roomId, String creatorId){

        this.roomId = roomId;
        this.creatorId = creatorId;
        this.SESSION_POOL = new HashMap<>();

    }

    public void quitRoom(String userId){

        this.SESSION_POOL.remove(userId);

    }

    public void joinRoom(String userId, Session session){

        this.SESSION_POOL.put(userId, session);

    }

    public int size(){
        return SESSION_POOL.size();
    }

    public Set<String> getUserIds(){

        return this.SESSION_POOL.keySet();

    }
    public Collection<Session> getSessions(){
        return SESSION_POOL.values();
    }

}
