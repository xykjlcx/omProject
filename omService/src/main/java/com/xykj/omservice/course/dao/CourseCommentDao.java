package com.xykj.omservice.course.dao;

import com.xykj.ombase.bases.BaseJpaDao;
import com.xykj.omservice.course.po.TCourseCommentPo;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseCommentDao extends BaseJpaDao<TCourseCommentPo,Integer> {
}
