package com.example.demo.mapper;

import com.example.demo.pojo.Classes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author Bincolo
* @description 针对表【classes】的数据库操作Mapper
* @createDate 2023-04-03 17:27:48
* @Entity com.example.demo.pojo.Classes
*/

@Repository
public interface ClassesMapper extends BaseMapper<Classes> {

    public List<Classes> selectAllClassesByStudentId(@Param("studentId") Long studentId);


}




