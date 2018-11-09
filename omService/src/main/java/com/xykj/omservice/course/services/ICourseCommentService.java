package com.xykj.omservice.course.services;

import com.xykj.omservice.course.po.TCourseCommentPo;
import org.springframework.data.domain.Page;
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

    /**
     * 新增一条评论记录
     * @param tCourseCommentPo
     * @return
     */
    void addComment(TCourseCommentPo tCourseCommentPo) throws Exception;

    /**
     * 查询某用户的所有评论
     * @param userId
     * @return
     */
    List<TCourseCommentPo> findAllByUserId(int userId) throws RuntimeException;

    /**
     * 获取评论数据
     * @param pageable
     * @return
     */
    Page<TCourseCommentPo> getCommentsPage(Pageable pageable) throws RuntimeException;

    /**
     * 删除一条评论
     * @param commentId
     * @throws RuntimeException
     */
    void deleteOneComment(Integer commentId) throws RuntimeException;

}
