package com.xykj.omapp.business.impl;

import com.xykj.omapp.business.ICourseBusiness;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.services.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseBusinessImpl implements ICourseBusiness {

    @Autowired
    CourseServiceImpl courseService;


    @Override
    public List<TCoursePo> getRecommendCourse() {
        try {
//            List<TCoursePo> coursePoList = courseService.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TCoursePo> getGuessLikeCourse() {
        return null;
    }



}
