package com.xykj.omservice.course.services;

import com.xykj.omservice.course.po.TCoursePo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICourService {

    /**
     * 分页查询所有
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


    /**
     * 编辑课程
     * @param tCoursePo
     * @throws RuntimeException
     */
    void editCourse(TCoursePo tCoursePo) throws RuntimeException;

    /**
     * 获取课程总数量
     * @return
     */
    Long getCourseCount();

    /**
     * 对课程进行上架/下架
     * @param courseId
     * @param isPutAway
     * @throws RuntimeException
     */
    void editCourseIsPutAway(int courseId,boolean isPutAway) throws RuntimeException;

    /**
     * 删除一条课程记录
     * @param courseId
     */
    void deleteCourseItem(int courseId) throws RuntimeException;


    /**
     * 根据课程id获取课程信息
     * @param courseId
     * @return
     * @throws RuntimeException
     */
    TCoursePo getCourseInfoById(int courseId) throws RuntimeException;

}
