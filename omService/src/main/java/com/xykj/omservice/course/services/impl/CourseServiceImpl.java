package com.xykj.omservice.course.services.impl;

import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.ombase.utils.error.OceanException;
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
    public List<TCoursePo> findAllByPage(Pageable pageable) throws Exception {
        Page<TCoursePo> poPage = courseDao.findAll(pageable);
        List<TCoursePo> coursePoList = poPage.getContent();
        if (OceanOperationUtil.isNullOrEmptyForCollection(coursePoList)){
            throw new OceanException("findAllByWeightPage() 获取所有课程列表为空");
        }
        return coursePoList;
    }

    @Override
    public List<TCoursePo> findByClassifyIdAndPage(int classifyId, Pageable pageable) throws Exception {
        List<TCoursePo> coursePoList = null;
        if (classifyId == 0){
            coursePoList = courseDao.findAll(pageable).getContent();
        }else {
            coursePoList = courseDao.findAllByClassifyId(classifyId,pageable);
            TCourseClassifyPo courseClassifyPo = courseClassifyDao.findById(classifyId).get();
            if (courseClassifyPo == null){
                throw new OceanException("根据分类查询课程失败，分类不存在");
            }
        }
        if (OceanOperationUtil.isNullOrEmptyForCollection(coursePoList)){
            throw new OceanException("没有查询到该分类下的课程");
        }
        return coursePoList;
    }
}
