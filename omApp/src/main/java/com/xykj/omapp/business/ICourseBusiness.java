package com.xykj.omapp.business;

import com.xykj.omservice.course.po.TCoursePo;
import org.springframework.stereotype.Service;

@Service
public interface ICourseBusiness {

    TCoursePo findAllCourse();

}
