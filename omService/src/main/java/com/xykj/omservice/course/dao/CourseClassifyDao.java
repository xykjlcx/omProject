package com.xykj.omservice.course.dao;

import com.xykj.omservice.bases.BaseJpaDao;
import com.xykj.omservice.course.po.TCourseClassifyPo;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CourseClassifyDao extends BaseJpaDao<TCourseClassifyPo,Integer> {

    List<TCourseClassifyPo> findByParentId(int parentId);

}
