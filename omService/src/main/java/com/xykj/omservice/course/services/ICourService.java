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
    List<TCoursePo> findByClassifyIdAndPage(int classifyId,Pageable pageable) throws Exception;


    /**
     * 搜索课程
     * @param courseName
     * @param pageable
     * @return
     */
    List<TCoursePo> searchCourseByNameForPage(String courseName,Pageable pageable)throws RuntimeException;

    /**
     * 添加新课程
     * @param tCoursePo
     * @throws RuntimeException
     */
    void addNewCourse(TCoursePo tCoursePo) throws RuntimeException;

    void editCourse(TCoursePo tCoursePo) throws RuntimeException;

}
