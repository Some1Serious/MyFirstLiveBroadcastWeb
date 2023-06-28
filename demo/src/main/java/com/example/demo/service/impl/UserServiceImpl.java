package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserSample;
import com.example.demo.service.UserService;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Bincolo
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-03-26 22:58:40
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    public boolean checkPassword(User user){

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(User::getAccountNumber, user.getAccountNumber());
        lambdaQueryWrapper.eq(User::getPassword, user.getPassword()).eq(User::getRole, user.getRole());

        long count = super.count(lambdaQueryWrapper);

        return count == 1;

    }
    public User getByAccountNumber(String accountNumber){


        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAccountNumber, accountNumber);

        return getOne(lambdaQueryWrapper);

    }

    public boolean updateByAccountNumber(User user){

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAccountNumber, user.getAccountNumber());

        return update(user, lambdaQueryWrapper);

    }


    public User getUserByRequest(HttpServletRequest request){

        HttpSession session = request.getSession();
        Object res = session.getAttribute("user");
        return getById((Long) res);

    }

    public Long getUserIdByRequest(HttpServletRequest request){

        HttpSession session = request.getSession();
        Object res = session.getAttribute("user");
        return (Long) res;

    }

    public List<User> getSampleUserList(LambdaQueryWrapper<User> queryWrapper){

        queryWrapper.select(User::getId, User::getRole, User::getSex, User::getNickname);
        return this.list(queryWrapper);

    }

    public User getSampleUser(LambdaQueryWrapper<User> queryWrapper){

        queryWrapper.select(User::getId, User::getRole, User::getSex, User::getNickname);
        return this.getOne(queryWrapper);

    }






}




