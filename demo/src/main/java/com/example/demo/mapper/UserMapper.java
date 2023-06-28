package com.example.demo.mapper;

import com.example.demo.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.UserSample;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author Bincolo
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-03-26 22:58:40
* @Entity com.example.demo.pojo.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {



}




