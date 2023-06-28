package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.ClassCourseMapper;
import com.example.demo.pojo.ClassCourse;
import com.example.demo.pojo.Course;
import com.example.demo.service.CourseService;
import com.example.demo.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* @author Bincolo
* @description 针对表【course】的数据库操作Service实现
* @createDate 2023-04-05 22:48:34
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

    @Autowired
    ClassCourseMapper classCourseMapper;

    @Autowired
    CourseMapper courseMapper;



    public List<Course> listByClassId(Long ClassId){
        return baseMapper.selectByClassId(ClassId);
    }

    public List<Course> listByUserId(Long UserId){return baseMapper.selectByUserId(UserId);}

    @Transactional
    public boolean addCourse(Course course){

        if (save(course)){
            ClassCourse classCourse = new ClassCourse();
            classCourse.setCourseId(course.getId());
            classCourse.setClassId(course.getClassId());
            return (classCourseMapper.insert(classCourse) > 0);
        }

        return false;

    }



}




