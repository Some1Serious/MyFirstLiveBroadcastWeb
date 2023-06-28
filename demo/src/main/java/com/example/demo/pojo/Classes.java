package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName classes
 */
@TableName(value ="classes")
@Data
public class Classes implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 
     */
    private Long teacherId;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private Date startTime;

    /**
     * 
     */
    private Date endTime;

    @TableField(exist = false)
    private String teacherName;

    @TableField(exist = false)
    private List<Course> courses;

    /**
     * 
     */

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}