package com.xykj.omservice.course.services.impl;

import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.services.ICourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements ICourService {


    @Autowired
    CourseDao courseDao;

    @Override
    public TCoursePo findAll() {
//        TCoursePo coursePo = new TCoursePo();
//        coursePo.setCourseName("Jass课程");
//        return coursePo;
        return courseDao.findAll().get(0);
    }


}
