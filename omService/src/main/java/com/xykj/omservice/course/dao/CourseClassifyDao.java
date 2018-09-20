package com.xykj.omservice.course.dao;

import com.xykj.ombase.bases.BaseJpaDao;
import com.xykj.omservice.course.po.TCourseClassifyPo;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseClassifyDao extends BaseJpaDao<TCourseClassifyPo,Integer> {
}
