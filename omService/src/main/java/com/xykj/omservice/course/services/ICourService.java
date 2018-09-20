package com.xykj.omservice.course.services;

import com.xykj.omservice.course.po.TCoursePo;
import org.springframework.stereotype.Service;

@Service
public interface ICourService {

    TCoursePo findAll();

}
