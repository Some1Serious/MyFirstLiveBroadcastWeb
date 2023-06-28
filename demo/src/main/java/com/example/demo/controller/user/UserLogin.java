package com.example.demo.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.pojo.User;
import com.example.demo.pojo.result.Result;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserLogin {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/register")
    public Result register(@RequestBody User user){

        System.out.println(user);

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        // 检测账号是否存在
        queryWrapper.eq(User::getAccountNumber, user.getAccountNumber());

        if (userService.count(queryWrapper) != 0){

            Result result = new Result(HttpStatus.BAD_REQUEST, "学号(工号)重复");
            return result;

        }

        // 检测邮箱是否存在
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, user.getEmail());

        if (userService.count(queryWrapper) != 0){

            Result result = new Result(HttpStatus.BAD_REQUEST, "账号重复");
            return result;

        }

        //添加数据
        boolean save = userService.save(user);

        if (!save){
            Result result = new Result(HttpStatus.BAD_REQUEST, "未知错误");
            return result;
        } else {
            return new Result(HttpStatus.OK, "成功!");
        }



    }

    @RequestMapping("/logIn")
    public Result<User> checkLogin(@RequestBody User user, HttpServletRequest request){

        System.out.println(user);
        // 检测密码是否正确
        boolean b = userService.checkPassword(user);

        User user1 = userService.getOne(new QueryWrapper<>(user));

//        userService.

        if (b){
            HttpSession session=request.getSession(true);
            session.setAttribute("user", user1.getId());
            System.out.println("已登录:" + session.getAttribute("user"));
        }

        return (new Result<User>(b, user1));
    }



}
