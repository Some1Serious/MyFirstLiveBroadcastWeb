package com.example.demo.controller.classes;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.pojo.Classes;
import com.example.demo.pojo.Course;
import com.example.demo.pojo.Schedules;
import com.example.demo.pojo.User;
import com.example.demo.pojo.result.Result;
import com.example.demo.service.CourseService;
import com.example.demo.service.impl.ClassesServiceImpl;
import com.example.demo.service.impl.CourseServiceImpl;
import com.example.demo.service.impl.SchedulesServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import org.apache.ibatis.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.logging.LoggingMXBean;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@RestController
@RequestMapping("/class")
public class ClassesController {

    @Autowired
    ClassesServiceImpl classesService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    SchedulesServiceImpl schedulesService;

    @Autowired
    CourseServiceImpl courseService;

    @RequestMapping("/get")
    public Result<Classes> getClazzById(@RequestBody Classes classes){

        Classes byId = classesService.getFullById(classes.getId());

        if (byId != null){
            return new Result<Classes>(true, byId);
        }else {
            return new Result<>(HttpStatus.NOT_FOUND, null);
        }

    }

    @RequestMapping("/get/teacher/{id}")
    public Result<List<Classes>> getClazzByTeacher(@PathVariable("id") Long id){

        List<Classes> list = classesService.showTeacherClass(id);

        if (list.size() == 0){
            return new Result<>(HttpStatus.NOT_FOUND);
        }else {
            return new Result<>(true, list);
        }



    }

    @RequestMapping("/join")
    public Result joinClass(@RequestBody Classes classes, HttpServletRequest request){

        User user = userService.getUserByRequest(request);
        Long classesId = classes.getId();
        Schedules schedules = new Schedules(user.getId(), classesId);

        // 要判断是否已经重复添加
        boolean unique = schedulesService.isUnique(schedules);

        if (!unique){
            return new Result(HttpStatus.ACCEPTED, "用户以添加,不能重复添加");
        }

        schedulesService.save(schedules);
        return new Result(true);


    }

    @RequestMapping("/getAll")
    public Result<List<Classes>> getAllClass(HttpServletRequest request){

        User user = userService.getUserByRequest(request);
        List<Classes> allClassesByUserId = schedulesService.getAllClassesByUserId(user.getId());

        if (allClassesByUserId.size() == 0){
            return new Result<>(HttpStatus.NOT_FOUND,"未找到任何班级");
        }else{
            return new Result<>(true, allClassesByUserId);
        }


    }

    @DeleteMapping("/{id}")
    public Result removeClass(@PathVariable("id") Long classId, HttpServletRequest request){


        Schedules schedules = new Schedules(userService.getUserIdByRequest(request), classId);
        boolean remove = schedulesService.remove(new QueryWrapper<>(schedules));

        return new Result(remove);

    }

    @DeleteMapping("/{id}/teacher")
    public Result teacherRemoveClass(@PathVariable("id") Long classId, HttpServletRequest request){


        if (classesService.getById(classId).getTeacherId().equals(userService.getUserIdByRequest(request)) ){
            boolean b = classesService.removeClass(classId);
            return new Result(b, "成功!");
        }else {
            return new Result(false, "权限不足!");
        }




    }

    @GetMapping("/getAllClassAndCourses")
    public Result<List<Classes>> getAllClassAndCourses(HttpServletRequest request){

        Long userIdByRequest = userService.getUserIdByRequest(request);

        List<Classes> allClassesByUserId = schedulesService.getAllClassesByUserId(userIdByRequest);

        for (Classes classes : allClassesByUserId) {

            List<Course> courses = courseService.listByClassId(classes.getId());
            classes.setCourses(courses);

        }

        return new Result<List<Classes>>(true, allClassesByUserId);

    }

    @GetMapping("/getAllTeacher")
    public Result<List<Classes>> getAllTeacher(HttpServletRequest request){

        Long userIdByRequest = userService.getUserIdByRequest(request);

        return new Result<>(true, classesService.teacherTimeTable(userIdByRequest));

    }


    @PostMapping
    public Result<String> insertClass(@RequestBody Classes classes, HttpServletRequest request){

        User userByRequest = userService.getUserByRequest(request);
        classes.setTeacherName(userByRequest.getNickname());
        classes.setTeacherId(userByRequest.getId());

        classesService.save(classes);
        return new Result<>(true,"创建班级成功");

    }

    @PutMapping
    public Result<String> updateClass(@RequestBody Classes classes, HttpServletRequest request){

        Long userIdByRequest = userService.getUserIdByRequest(request);

        // 检测权限
        if (classesService.getById(classes.getId()).getTeacherId().equals(userIdByRequest)){

            classesService.updateById(classes);
            return new Result<>(true,"更新成功");

        }

        return new Result<>(false, "权限不足!");

    }


}
