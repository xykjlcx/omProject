package com.xykj.omservice.user.services.impl;

import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.user.dao.UserCourseStudyDao;
import com.xykj.omservice.user.po.TUserCourseStudyPo;
import com.xykj.omservice.user.services.IUserCourseCollectService;
import com.xykj.omservice.user.services.IUserCourseStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void save(TUserCourseStudyPo data) {

    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public List<TUserCourseStudyPo> findAll() {
        return null;
    }

    @Override
    public TUserCourseStudyPo findById() {
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
}
