package com.example.demo.controller.webSocket.pojo;

import com.example.demo.pojo.User;
import lombok.Data;

import java.util.List;

@Data
public class UserList {

    private User creator;

    // ID : User(role, nickname, )
    private List<User> audienceIdList;

    public UserList(User creator, List<User> audienceIdList) {
        this.creator = creator;
        this.audienceIdList = audienceIdList;
    }
}
