package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName schedules
 */
@TableName(value ="schedules")
@Data
public class Schedules implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 
     */
    private Long studentId;

    /**
     * 
     */
    private Long classId;

    public Schedules(){

    }

    public Schedules(Long studentId, Long classId) {
        this.studentId = studentId;
        this.classId = classId;
    }

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}