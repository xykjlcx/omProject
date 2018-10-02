package com.xykj.omservice.course.services.impl;

import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.course.dao.CourseClassifyDao;
import com.xykj.omservice.course.po.TCourseClassifyPo;
import com.xykj.omservice.course.services.ICourseClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouseClassifyServiceImpl implements ICourseClassifyService {

    @Autowired
    CourseClassifyDao courseClassifyDao;


    @Override
    public List<TCourseClassifyPo> findAnyLevel(int parentId) throws Exception {
        List<TCourseClassifyPo> courseClassifyPoList;
        if (parentId < 0){
            throw new NullPointerException("该分类不存在");
        }
        courseClassifyPoList = courseClassifyDao.findAllByParentIdOrderBySequenceAsc(parentId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(courseClassifyPoList)){
            throw new NullPointerException("该分类下不存在子分类");
        }
        return courseClassifyPoList;
    }

    @Override
    public TCourseClassifyPo findById(int id) {
        TCourseClassifyPo courseClassifyPo = courseClassifyDao.findById(id).get();
        if (courseClassifyPo == null){
            throw new NullPointerException("找不到该分类，分类id为：" + id);
        }
        return courseClassifyPo;
    }


}
