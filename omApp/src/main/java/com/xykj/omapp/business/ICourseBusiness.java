package com.xykj.omapp.business;

import com.xykj.omapp.vo.CourseCommentVo;
import com.xykj.omservice.course.po.TCoursePo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ICourseBusiness {


    /**
     * 获取课程的所有章节信息
     * @param courseId
     * @return
     */
    Map<String,Object> getChapterAndSection(int courseId);


    List<CourseCommentVo> getCourseCommentsByCourseIdForPage(int courseId, Pageable pageable) throws Exception;

}
