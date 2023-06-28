package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName class_course
 */
@TableName(value ="class_course")
@Data
public class ClassCourse implements Serializable {
    /**
     * 
     */
    @TableId
    private Long id;

    /**
     * 
     */
    private Long classId;

    /**
     * 
     */
    private Long courseId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}