package com.example.demo.controller.user;

import com.example.demo.pojo.User;
import com.example.demo.pojo.result.Result;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserGetterSetter {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/get")
    public Result<User> getUser(HttpServletRequest request){

        User userByRequest = userService.getUserByRequest(request);


        return new Result<User>(true,userByRequest);

    }

    @RequestMapping("/set")
    public Result<User> setUser(@RequestBody User user, HttpServletRequest request){

        System.out.println("-----------------------------");
        System.out.println(user);

        user.setId((Long) request.getSession().getAttribute("user"));



        boolean b = userService.updateById(user);

        return new Result<User>(b);

    }


}
