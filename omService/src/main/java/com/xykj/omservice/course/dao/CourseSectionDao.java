package com.xykj.omservice.course.dao;

import com.xykj.omservice.bases.BaseJpaDao;
import com.xykj.omservice.course.po.TCourseSectionPo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseSectionDao extends BaseJpaDao<TCourseSectionPo,Integer> {

    /**
     * 根据课程id和parentid查询,并正序排序
     * parentid == 0时，查询所有章
     * @param courseId
     * @param parentId
     * @return
     */
    List<TCourseSectionPo> findAllByCourseIdAndParentIdOrderBySequenceAsc(int courseId,int parentId);

    /**
     * 查询课程的所有章节(无序)
     * @param courseId
     * @return
     */
    List<TCourseSectionPo> findAllByCourseId(int courseId);

}
