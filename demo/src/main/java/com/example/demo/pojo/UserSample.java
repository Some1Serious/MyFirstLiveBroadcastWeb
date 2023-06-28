package com.example.demo.pojo;

import com.example.demo.pojo.Enum.Role;
import com.example.demo.pojo.Enum.Sex;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class UserSample {

    // 用来传输简化之后的User信息

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String nickname;

    private Sex sex;

    private Role role;

}
