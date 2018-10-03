package com.xykj.omservice.course.services.impl;

import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.course.dao.CourseCommentDao;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.course.po.TCourseCommentPo;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.services.ICourseCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ocean
 * @Title: CourseCommentServiceImpl
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/3上午10:39
 */
@Service
public class CourseCommentServiceImpl implements ICourseCommentService {

    @Autowired
    CourseCommentDao courseCommentDao;
    @Autowired
    CourseDao courseDao;

    @Override
    public List<TCourseCommentPo> getAllCommentByCourseIdForPage(int courseId, Pageable pageable) {
        List<TCourseCommentPo> tCourseCommentPoList = null;
        List<TCoursePo> tCoursePoList = courseDao.findAllById(courseId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(tCoursePoList)){
            throw new NullPointerException("无法查询该课程的评论，因为该课程不存在");
        }
        tCourseCommentPoList = courseCommentDao.findAllByCourseIdOrderByCreateTimeDesc(courseId,pageable);
        if (OceanOperationUtil.isNullOrEmptyForCollection(tCourseCommentPoList)){
            throw new RuntimeException("该课程暂时没有评论");
        }
        return tCourseCommentPoList;
    }

}
