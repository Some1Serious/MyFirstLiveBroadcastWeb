package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.example.demo.pojo.Enum.Role;
import com.example.demo.pojo.Enum.Sex;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 
     */
    private String accountNumber;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String nickname;

    /**
     * 
     */
    // 无论值为多少都会更新(包括null)
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String school;

    /**
     * 
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String telephone;

    /**
     * 
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String email;

    /**
     * 
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer age;

    /**
     * 
     */
    private Sex sex;

    /**
     * 
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String personalProfile;

    /**
     * 
     */
    private Role role;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    public User(String accountNumber, String password, String nickname, String school, String telephone, String email, Integer age, Sex sex, String personalProfile, Role role) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.nickname = nickname;
        this.school = school;
        this.telephone = telephone;
        this.email = email;
        this.age = age;
        this.sex = sex;
        this.personalProfile = personalProfile;
        this.role = role;
    }

    public User(){

    }
}