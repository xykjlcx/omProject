package com.xykj.omservice.course.dao;

import com.xykj.omservice.bases.BaseJpaDao;
import com.xykj.omservice.course.po.TCoursePo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao extends BaseJpaDao<TCoursePo,Integer> {

    List<TCoursePo> findByClassifyId(int classifyId);

    /**
     * 根据id或者parentid查询课程
     * @param classifyId
     * @param pageable
     * @return
     */
    @Query(
            "select tc from TCoursePo tc where tc.classifyId in (select tcc.id from TCourseClassifyPo tcc where tcc.id=?1 or tcc.parentId=?1)"
    )
    List<TCoursePo> findAllByClassifyId(int classifyId,Pageable pageable);

}
