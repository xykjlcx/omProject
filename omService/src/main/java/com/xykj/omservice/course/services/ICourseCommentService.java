package com.xykj.omservice.course.services;

import com.xykj.omservice.course.po.TCourseCommentPo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ocean
 * @Title: ICourseCommentService
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/3上午10:39
 */
@Service
public interface ICourseCommentService {

    /**
     * 根据课程获取所有评论（分页）
     * @param courseId
     * @return
     */
    List<TCourseCommentPo> getAllCommentByCourseIdForPage(int courseId,Pageable pageable);

}
