package com.xykj.omservice.course.services.impl;

import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.course.dao.CourseClassifyDao;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.course.po.TCourseClassifyPo;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.services.ICourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourService {


    @Autowired
    CourseDao courseDao;

    @Autowired
    CourseClassifyDao courseClassifyDao;


    @Override
    public List<TCoursePo> findAllByWeightPage(Integer page, Integer size) throws Exception {
        Pageable pageable = new PageRequest(page,size);
        Page<TCoursePo> poPage = courseDao.findAll(pageable);
        List<TCoursePo> coursePoList = poPage.getContent();
        if (OceanOperationUtil.isNullOrEmptyForCollection(coursePoList)){
            throw new NullPointerException("获取所有课程列表为空");
        }
        return coursePoList;
    }

    @Override
    public List<TCoursePo> findByClassifyId(int classifyId) throws Exception {
        List<TCoursePo> coursePoList;
        TCourseClassifyPo courseClassifyPo = courseClassifyDao.findById(classifyId).get();
        if (courseClassifyPo == null){
            throw new NullPointerException("根据分类查询课程失败，分类不存在");
        }
        coursePoList = courseDao.findByClassifyId(classifyId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(coursePoList)){
            throw new NullPointerException("查无此分类课程");
        }
        return coursePoList;
    }

    public String jass(){
        return "牛逼";
    }

}
