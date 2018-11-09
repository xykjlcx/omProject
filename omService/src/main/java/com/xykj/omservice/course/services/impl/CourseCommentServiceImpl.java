package com.xykj.omservice.course.services.impl;

import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.course.dao.CourseCommentDao;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.course.dao.CourseSectionDao;
import com.xykj.omservice.course.po.TCourseCommentPo;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.po.TCourseSectionPo;
import com.xykj.omservice.course.services.ICourseCommentService;
import com.xykj.omservice.user.dao.UserDao;
import com.xykj.omservice.user.po.TUserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    @Autowired
    UserDao userDao;
    @Autowired
    CourseSectionDao courseSectionDao;

    @Override
    public List<TCourseCommentPo> getAllCommentByCourseIdForPage(int courseId, Pageable pageable) {
        List<TCourseCommentPo> tCourseCommentPoList = null;
        List<TCoursePo> tCoursePoList = courseDao.findAllById(courseId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(tCoursePoList)){
            throw new RuntimeException("无法查询该课程的评论，因为该课程不存在");
        }
        tCourseCommentPoList = courseCommentDao.findAllByCourseIdOrderByCreateTimeDesc(courseId,pageable);
        if (OceanOperationUtil.isNullOrEmptyForCollection(tCourseCommentPoList)){
            throw new RuntimeException("该课程暂时没有评论");
        }
        System.out.println("随便我" + tCourseCommentPoList);
        return tCourseCommentPoList;
    }

    @Override
    public void addComment(TCourseCommentPo tCourseCommentPo) throws Exception {
        List<TUserPo> checkUserList = userDao.findAllById(tCourseCommentPo.getUserId());
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkUserList)){
            throw new RuntimeException("添加评论失败，该用户不存在");
        }
        List<TCoursePo> checkCourseList = courseDao.findAllById(tCourseCommentPo.getCourseId());
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkCourseList)){
            throw new RuntimeException("添加评论失败，该课程不存在");
        }
        List<TCourseSectionPo> checkSectionList = courseSectionDao.findAllByIdAndCourseId(tCourseCommentPo.getSectionId(),tCourseCommentPo.getCourseId());
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkSectionList)){
            // throw new RuntimeException("添加评论失败，该课程章节不存在");
            // 由抛出异常改为 将sectionid设为默认值0，然后添加这条记录
            tCourseCommentPo.setSectionId(-1);
        }
        tCourseCommentPo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        courseCommentDao.save(tCourseCommentPo);
    }

    @Override
    public List<TCourseCommentPo> findAllByUserId(int userId) throws RuntimeException {
        List<TUserPo> checkUserList = userDao.findAllById(userId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkUserList)){
            throw new RuntimeException("失败，用户不存在");
        }
        List<TCourseCommentPo> courseCommentPoList = courseCommentDao.findAllByUserIdOrderByCreateTimeDesc(userId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(courseCommentPoList)){
            throw new RuntimeException("该用户未发表任何评论");
        }
        return courseCommentPoList;
    }

    @Override
    public Page<TCourseCommentPo> getCommentsPage(Pageable pageable) throws RuntimeException {
        Page<TCourseCommentPo> resultCommentList = courseCommentDao.findAll(pageable);
        if (OceanOperationUtil.isNullOrEmptyForCollection(resultCommentList.getContent())){
            throw new RuntimeException("暂无评论数据");
        }
        return resultCommentList;
    }

    @Override
    public void deleteOneComment(Integer commentId) throws RuntimeException {
        List<TCourseCommentPo> checkCommentList = courseCommentDao.findAllById(commentId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkCommentList)){
            throw new RuntimeException("删除失败，该评论不存在");
        }
        courseCommentDao.delete(checkCommentList.get(0));
    }

}
