package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.ClassCourseMapper;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.pojo.ClassCourse;
import com.example.demo.pojo.Classes;
import com.example.demo.pojo.Course;
import com.example.demo.pojo.User;
import com.example.demo.service.ClassesService;
import com.example.demo.mapper.ClassesMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* @author Bincolo
* @description 针对表【classes】的数据库操作Service实现
* @createDate 2023-04-03 17:27:48
*/
@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes>
    implements ClassesService{

    @Autowired
    UserService userService;

    @Autowired
    ClassesMapper classesMapper;

    @Autowired
    ClassCourseMapper classCourseMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    CourseServiceImpl courseService;

    /**
     * 包括教师名称
     * @param id
     * @return
     */
    public Classes getFullById(Long id){

        Classes classes1 = getById(id);

        if (classes1 == null){
            return null;
        }
        User teacher = userService.getById(classes1.getTeacherId());


        classes1.setTeacherName(teacher.getNickname());
        return classes1;
    }

    public List<Classes> showTeacherClass(Long teacherId){

        User byId = userService.getById(teacherId);

        LambdaQueryWrapper<Classes> classWrapper = new LambdaQueryWrapper<>();
        classWrapper.eq(Classes::getTeacherId, teacherId);

        List<Classes> classes = classesMapper.selectList(classWrapper);

        for (Classes aClass : classes) {
            aClass.setTeacherName(byId.getNickname());
        }

        return classes;


    }

    @Transactional
    public boolean removeClass(Long classId){

        LambdaQueryWrapper<ClassCourse> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClassCourse::getClassId, classId);
        List<ClassCourse> classCourses = classCourseMapper.selectList(wrapper);

        if (classCourses.size() > 0){
            List<Long> courseIds = new ArrayList<>();
            for (ClassCourse classCours : classCourses) {
                courseIds.add(classCours.getCourseId());
            }


            courseMapper.deleteBatchIds(courseIds);
        }



        return classesMapper.deleteById(classId) >= 1;


    }

    public List<Classes> teacherTimeTable(Long teacherId){

        LambdaQueryWrapper<Classes> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Classes::getTeacherId, teacherId);
        List<Classes> classes = classesMapper.selectList(wrapper);

        for (Classes cl : classes) {

            List<Course> courses = courseService.listByClassId(cl.getId());
            cl.setCourses(courses);

        }
        return classes;
    }



}




