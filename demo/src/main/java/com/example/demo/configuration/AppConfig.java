package com.example.demo.configuration;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("com.example.demo.mapper")
public class AppConfig {


}
