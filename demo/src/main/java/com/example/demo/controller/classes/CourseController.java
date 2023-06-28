package com.example.demo.controller.classes;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.pojo.Course;
import com.example.demo.pojo.result.Result;
import com.example.demo.service.impl.CourseServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/course")
@RestController
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;

    @Autowired
    UserServiceImpl userService;


    @GetMapping("/classAll/{classId}")
    public Result<List<Course>> getAllCourseByClassId(@PathVariable("classId") Long classId){

        List<Course> courses = courseService.listByClassId(classId);
        return new Result<>(true, courses);

    }

    @GetMapping("/userAll")
    public Result<List<Course>> getAllCourseByUserId(HttpServletRequest request){

        Long userId = userService.getUserIdByRequest(request);
        List<Course> courses = courseService.listByUserId(userId);

        return new Result<>(true, courses);

    }

    @PostMapping("")
    public Result<String> addCourse(@RequestBody Course course, HttpServletRequest request){

        Long userIdByRequest = userService.getUserIdByRequest(request);
        course.setTeacherId(userIdByRequest);

        if (courseService.addCourse(course)){
            return new Result<>(true, "创建课程成功!");
        } else {
            return new Result<>(false, "创建失败!");
        }


    }

    @DeleteMapping("/{courseId}")
    public Result<String> deleteCourse(@PathVariable("courseId") String courseId, HttpServletRequest request){

        Long userIdByRequest = userService.getUserIdByRequest(request);

        Course course = courseService.getById(courseId);

        if (course.getTeacherId().equals(userIdByRequest)){

            courseService.removeById(courseId);
            return new Result<>(true, "删除成功");

        }

        return new Result<>(false, "权限不足, 删除失败");

    }

}
