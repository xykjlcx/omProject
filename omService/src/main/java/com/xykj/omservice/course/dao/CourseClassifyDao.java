package com.xykj.omservice.course.dao;

import com.xykj.ombase.bases.BaseJpaDao;
import com.xykj.omservice.course.po.TCourseClassifyPo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseClassifyDao extends BaseJpaDao<TCourseClassifyPo,Integer> {

    List<TCourseClassifyPo> findByParentId(int parentId);

}
