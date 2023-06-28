package com.example.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.controller.webSocket.pojo.Enum.MessageType;
import com.example.demo.controller.webSocket.pojo.Message;
import com.example.demo.pojo.User;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@SpringBootTest
public class test {

    @Autowired
    UserServiceImpl userService;

    @Test
    public void test(){

//        Map<Integer, Integer> SESSION_POOL = new HashMap<>();
//
//        SESSION_POOL.put(1,2);
//        SESSION_POOL.put(1,3);
//        System.out.println(SESSION_POOL);


        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.between(User::getId, 0, 100);
        List<User> sampleUserList = userService.getSampleUserList(queryWrapper);
        System.out.println(sampleUserList);


    }


}
