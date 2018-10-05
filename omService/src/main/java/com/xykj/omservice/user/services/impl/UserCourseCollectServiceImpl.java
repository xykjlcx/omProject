package com.xykj.omservice.user.services.impl;

import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.user.dao.UserCourseCollectDao;
import com.xykj.omservice.user.dao.UserDao;
import com.xykj.omservice.user.po.TUserCourseCollectPo;
import com.xykj.omservice.user.po.TUserPo;
import com.xykj.omservice.user.services.IUserCourseCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ocean
 * @Title: UserCourseCollectServiceImpl
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/5上午9:56
 */
@Service
public class UserCourseCollectServiceImpl implements IUserCourseCollectService {

    @Autowired
    UserDao userDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    UserCourseCollectDao userCourseCollectDao;

    @Override
    public List<TCoursePo> queryCollectCourseByUserId(int userId) throws RuntimeException {
        // 返回我的收藏列表
        List<TUserPo> checkUserList = userDao.findAllById(userId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkUserList)){
            throw new RuntimeException("获取我的收藏失败，用户不存在");
        }
        List<TUserCourseCollectPo> userCourseCollectPoList = userCourseCollectDao.findAllByUserIdOrderByCollectTimeDesc(userId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(userCourseCollectPoList)){
            throw new RuntimeException("该用户并未收藏任何课程");
        }
        List<TCoursePo> coursePoList = new ArrayList<>();
        userCourseCollectPoList.forEach(tUserCourseCollectPo -> {
            coursePoList.add(courseDao.findById(tUserCourseCollectPo.getCourseId()).get());
        });
        return coursePoList;
    }

    @Override
    public void addCollectCourses(int userId, int courseId) {
        // 添加收藏课程
        List<TUserPo> checkUserList = userDao.findAllById(userId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkUserList)){
            throw new RuntimeException("收藏失败，用户不存在");
        }
        List<TCoursePo> checkCourseList = courseDao.findAllById(courseId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkCourseList)){
            throw new RuntimeException("收藏失败，课程不存在");
        }
        List<TUserCourseCollectPo> userCourseCollectPoList = userCourseCollectDao.findAllByUserIdAndCourseId(userId,courseId);
        if (OceanOperationUtil.isNotNullOrEmptyForCollection(userCourseCollectPoList)){
            throw new RuntimeException("您已经收藏过该课程");
        }
        TUserCourseCollectPo newCollectCourse = new TUserCourseCollectPo();
        newCollectCourse.setUserId(userId);
        newCollectCourse.setCourseId(courseId);
        newCollectCourse.setCollectTime(new Timestamp(System.currentTimeMillis()));
        userCourseCollectDao.saveAndFlush(newCollectCourse);    // 保存成功
    }

    @Override
    public void save(TUserCourseCollectPo data) throws Exception {

    }

    @Override
    public void deleteById(Integer integer) throws Exception {

    }

    @Override
    public List<TUserCourseCollectPo> findAll() {
        return null;
    }

    @Override
    public TUserCourseCollectPo findById(Integer integer) throws Exception {
        return null;
    }
}
