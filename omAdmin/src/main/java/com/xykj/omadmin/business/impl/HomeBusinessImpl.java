package com.xykj.omadmin.business.impl;

import com.xykj.omadmin.business.IHomeBusiness;
import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.course.dao.CourseCommentDao;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.course.po.TCourseCommentPo;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ocean
 * @Title: HomeBusinessImpl
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/11/1011:38 AM
 */
@Service
public class HomeBusinessImpl implements IHomeBusiness {

    @Autowired
    UserDao userDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    CourseCommentDao courseCommentDao;

    @Override
    public Map<String, Object> getHomeData() throws RuntimeException {
        Map<String,Object> data = new HashMap<>();
        Long userCount = userDao.count();
        List<Object[]> bestCourseData = courseDao.findCourseByStudyCountBest();
        int bestCourseId = (int) bestCourseData.get(0)[0];
        List<TCoursePo> coursePoList = courseDao.findAllById(bestCourseId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(coursePoList)){
            // todo 未来应迭代为，最受欢迎不存在，就查询次受欢迎课程，直至没有
            throw new RuntimeException("最受欢迎的课程已经不存在啦");
        }
        TCourseCommentPo courseCommentPo = courseCommentDao.findRecentlyComment();
        data.put("userCount",userCount);
        data.put("bestCourse",coursePoList.get(0).getCourseName());
        data.put("bestNewComment",courseCommentPo.getCommentContent());
        return data;
    }
}
