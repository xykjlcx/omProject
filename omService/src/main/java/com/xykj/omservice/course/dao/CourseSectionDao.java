package com.xykj.omservice.course.dao;

import com.xykj.ombase.bases.BaseJpaDao;
import com.xykj.omservice.course.po.TCourseSectionPo;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseSectionDao extends BaseJpaDao<TCourseSectionPo,Integer> {
}
