package com.xykj.omapp.business.impl;

import com.xykj.omapp.business.ICourseBusiness;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.services.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseBusinessImpl implements ICourseBusiness {

    @Autowired
    CourseServiceImpl courseService;

    @Override
    public TCoursePo findAllCourse() {
        return courseService.findAll();
    }
}
