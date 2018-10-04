package com.xykj.omservice.user.services.impl;

import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.ombase.utils.error.OceanException;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.course.dao.CourseSectionDao;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.po.TCourseSectionPo;
import com.xykj.omservice.user.dao.UserCourseStudyDao;
import com.xykj.omservice.user.dao.UserDao;
import com.xykj.omservice.user.po.TUserCourseStudyPo;
import com.xykj.omservice.user.po.TUserPo;
import com.xykj.omservice.user.services.IUserCourseCollectService;
import com.xykj.omservice.user.services.IUserCourseStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ocean
 * @Title: UserCourseStudyServiceImpl
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/1下午1:23
 */
@Service
public class UserCourseStudyServiceImpl implements IUserCourseStudyService {

    @Autowired
    UserCourseStudyDao userCourseStudyDao;
    @Autowired
    UserDao userDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    CourseSectionDao courseSectionDao;

    @Override
    public void save(TUserCourseStudyPo data) throws Exception{
        int userId = data.getUserId();
        int courseId = data.getCourseId();
        int sectionId = data.getSectionId();
        try {
            TUserPo checkUser = userDao.findById(userId).get();
            TCoursePo checkCourse = courseDao.findById(courseId).get();
            TCourseSectionPo checkSection = courseSectionDao.findById(sectionId).get();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("添加用户课程学习关联失败，参数不合法");
        }
        List<TUserCourseStudyPo> checkAlreadyExist = userCourseStudyDao.findAllByUserIdAndCourseIdAndSectionId(userId,courseId,sectionId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkAlreadyExist)){
            // 不存在记录，可以添加新的学习记录
            data.setFirstStudyTime(new Timestamp(System.currentTimeMillis()));
            data.setLastStudyTime(new Timestamp(System.currentTimeMillis()));
            data.setCreateTime(new Timestamp(System.currentTimeMillis()));
            data.setStatus(0);
            userCourseStudyDao.save(data);
        }else {
            // 存在相同的记录，不可插入
            throw new RuntimeException("id为：《" + userId + "》的用户已经学习了课程：《" + courseId + "》不可重复学习该课程！");
        }
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public List<TUserCourseStudyPo> findAll() {
        return null;
    }

    @Override
    public TUserCourseStudyPo findById(Integer id) {
        return null;
    }

    @Override
    public List<TUserCourseStudyPo> getMyStudyCourseByUserId(int userId) throws Exception {
        List<Object[]> rows = userCourseStudyDao.findTUserCourseStudyPosByUserId(userId);
        List<TUserCourseStudyPo> userCourseStudyPoList = new ArrayList<>();
        if (OceanOperationUtil.isNotNullOrEmptyForCollection(rows)){
            rows.forEach(row -> {
                // row[0] 分组处理后的 courseId
                // row[1] 分组处理后的 last_study_time
                userCourseStudyPoList.add(userCourseStudyDao.findFirstByUserIdAndCourseIdOrderByLastStudyTimeDesc(userId, (Integer) row[0]));
            });
        }else {
            throw new RuntimeException( "用户：" + userId + ",并没有学习任何课程！");
        }
        // rows非空，这里就肯定非空
//        if (OceanOperationUtil.isNullOrEmptyForCollection(userCourseStudyPoList))
//            throw new RuntimeException("");
        return userCourseStudyPoList;
    }

    @Override
    public boolean isStudyCourse(int userId, int courseId) {
        List<TUserPo> userPoList = userDao.findAllById(userId);
        List<TCoursePo> coursePoList = courseDao.findAllById(courseId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(userPoList)){
            throw new NullPointerException("查询不到该用户");
        }
        if (OceanOperationUtil.isNullOrEmptyForCollection(coursePoList)){
            throw new NullPointerException("查询不到该课程");
        }
        List<TUserCourseStudyPo> tUserCourseStudyPoList = userCourseStudyDao.findAllByUserIdAndCourseId(userId,courseId);
        if (OceanOperationUtil.isNotNullOrEmptyForCollection(tUserCourseStudyPoList)){
            return true;
        }
        return false;
    }
}
