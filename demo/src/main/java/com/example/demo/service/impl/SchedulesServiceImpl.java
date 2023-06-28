package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.ClassesMapper;
import com.example.demo.pojo.Classes;
import com.example.demo.pojo.Schedules;
import com.example.demo.service.SchedulesService;
import com.example.demo.mapper.SchedulesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Bincolo
* @description 针对表【schedules】的数据库操作Service实现
* @createDate 2023-04-04 15:14:11
*/
@Service
public class SchedulesServiceImpl extends ServiceImpl<SchedulesMapper, Schedules>
    implements SchedulesService{

    @Autowired
    ClassesMapper classesMapper;

    public boolean isUnique(Schedules schedules){

        LambdaQueryWrapper<Schedules> lambdaQuery = new LambdaQueryWrapper<>();
        lambdaQuery.eq(Schedules::getClassId, schedules.getClassId())
                        .eq(Schedules::getStudentId, schedules.getStudentId());

        long count = count(lambdaQuery);

        return count == 0;

    }

    public List<Classes> getAllClassesByUserId(Long userId){

        return classesMapper.selectAllClassesByStudentId(userId);


    }

}




