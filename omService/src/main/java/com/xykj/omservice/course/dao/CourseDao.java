package com.xykj.omservice.course.dao;

import com.xykj.ombase.bases.BaseJpaDao;
import com.xykj.omservice.course.po.TCoursePo;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao extends BaseJpaDao<TCoursePo,Integer> {
}
