package com.xykj.omservice.course.dao;

import com.xykj.omservice.bases.BaseJpaDao;
import com.xykj.omservice.course.po.TCoursePo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao extends BaseJpaDao<TCoursePo,Integer> {

    List<TCoursePo> findByClassifyId(int classifyId);

}
