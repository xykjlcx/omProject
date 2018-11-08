package com.xykj.omservice.course.dao;

import com.xykj.omservice.bases.BaseJpaDao;
import com.xykj.omservice.course.po.TCourseClassifyPo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CourseClassifyDao extends BaseJpaDao<TCourseClassifyPo,Integer> {

    List<TCourseClassifyPo> findAllByParentIdOrderBySequenceAsc(int parentId);

    List<TCourseClassifyPo> findAllById(int classifyId);

    // 添加根据parentId查询其下最后一个分类的squee
    @Query(
            "SELECT max(tccp.sequence) from TCourseClassifyPo tccp where tccp.parentId = ?1"
    )
    Integer getMaxsequenceByParentId(int parentId);

}
