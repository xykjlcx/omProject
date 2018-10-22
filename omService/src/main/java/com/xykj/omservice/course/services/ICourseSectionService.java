package com.xykj.omservice.course.services;

import com.xykj.omservice.course.po.TCourseSectionPo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ocean
 * @Title: ICourseSectionService
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/2下午11:10
 */
@Service
public interface ICourseSectionService {

    /**
     * 根据课程id获取该课程的所有章节
     * @param courseId
     * @return
     * @throws Exception
     */
    Map<String,Object> getAllChapterAndSection(int courseId) throws Exception;

    /**
     * 为课程添加新的章节
     * @param tCourseSectionPo
     * @throws RuntimeException
     */
    void addNewSection(TCourseSectionPo tCourseSectionPo) throws RuntimeException;

    void editSection(TCourseSectionPo tCourseSectionPo) throws RuntimeException;

}
