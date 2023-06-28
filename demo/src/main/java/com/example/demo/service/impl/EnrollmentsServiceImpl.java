package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.pojo.Enrollments;
import com.example.demo.service.EnrollmentsService;
import com.example.demo.mapper.EnrollmentsMapper;
import org.springframework.stereotype.Service;

/**
* @author Bincolo
* @description 针对表【enrollments】的数据库操作Service实现
* @createDate 2023-04-03 17:27:48
*/
@Service
public class EnrollmentsServiceImpl extends ServiceImpl<EnrollmentsMapper, Enrollments>
    implements EnrollmentsService{

}




