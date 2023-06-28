package com.example.demo.mapper;

import com.example.demo.pojo.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author Bincolo
* @description 针对表【course】的数据库操作Mapper
* @createDate 2023-04-05 22:48:34
* @Entity com.example.demo.pojo.Course
*/
@Repository
public interface CourseMapper extends BaseMapper<Course> {

    public List<Course> selectByClassId(@Param("classId") Long classId);

    public List<Course> selectByUserId(@Param("userId") Long userId);

}




