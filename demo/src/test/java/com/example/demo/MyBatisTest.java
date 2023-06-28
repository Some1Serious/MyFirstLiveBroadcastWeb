package com.example.demo;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.mapper.ClassesMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Classes;
import com.example.demo.pojo.Enum.Sex;
import com.example.demo.pojo.User;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyBatisTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ClassesMapper classesMapper;

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testStudentMapper(){

        User user = new User("10000001", "100000000", "100000000", "suzhoudaxue",
                "100000000", "10000@qq.com", 0, Sex.UNKNOWN, "", null);


        int insert = userMapper.insert(user);
        System.out.println(insert);

    }

    @Test
    public void testStudentMapperUpdate(){

        User user = new User("10000001", "100000000", "100000000", "suzhoudaxue",
                "100000000", "100001@qq.com", null, Sex.UNKNOWN, "", null);

        user.setId(1640008201128665089L);

        userMapper.updateById(user);

    }


    @Test
    public void testClassMapper(){

        System.out.println("hello");
//        List<Classes> classes = classesMapper.selectAllClassesByStudentId(1L);
//        System.out.println(classes);

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.between(User::getId, 1, 10);
        System.out.println(userService.getSampleUserList(lambdaQueryWrapper));

    }

}
