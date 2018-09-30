package com.xykj.omservice.course.services;

import com.xykj.omservice.course.po.TCoursePo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICourService {

    /**
     *
     * @return
     */
    List<TCoursePo> findAllByPage(Pageable pageable) throws Exception;

    /**
     * 根据分类查询课程
     * @param classifyId
     * @return
     */
    List<TCoursePo> findByClassifyId(int classifyId) throws Exception;


}
