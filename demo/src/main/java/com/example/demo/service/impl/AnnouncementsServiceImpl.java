package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.pojo.Announcements;
import com.example.demo.service.AnnouncementsService;
import com.example.demo.mapper.AnnouncementsMapper;
import org.springframework.stereotype.Service;

/**
* @author Bincolo
* @description 针对表【announcements】的数据库操作Service实现
* @createDate 2023-04-03 17:27:48
*/
@Service
public class AnnouncementsServiceImpl extends ServiceImpl<AnnouncementsMapper, Announcements>
    implements AnnouncementsService{

}




