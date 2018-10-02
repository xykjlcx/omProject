package com.xykj.omapp.business.impl;

import com.xykj.omapp.business.IUserCourseBusiness;
import com.xykj.omapp.utils.PoConvertVo;
import com.xykj.omapp.vo.CourseVo;
import com.xykj.omapp.vo.UserCourseVo;
import com.xykj.ombase.utils.OceanDateUtil;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.course.dao.CourseSectionDao;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.po.TCourseSectionPo;
import com.xykj.omservice.user.po.TUserCourseStudyPo;
import com.xykj.omservice.user.services.impl.UserCourseStudyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ocean
 * @Title: UserCourseBusinessImpl
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/1下午5:45
 */
@Service
public class UserCourseBusinessImpl implements IUserCourseBusiness {

    @Autowired
    UserCourseStudyServiceImpl userCourseStudyService;
    @Autowired
    CourseDao courseDao;
    @Autowired
    CourseSectionDao courseSectionDao;

    @Override
    public List<UserCourseVo> getMyCoursesByUserId(int userId) throws Exception{
        List<UserCourseVo> userCourseVoList = new ArrayList<>();
        try {
            List<TUserCourseStudyPo> userCourseStudyPoList = userCourseStudyService.getMyStudyCourseByUserId(userId);
            for (int i = 0; i < userCourseStudyPoList.size(); i++) {
                TUserCourseStudyPo tUserCourseStudyPo = userCourseStudyPoList.get(i);
                TCoursePo tCoursePo = courseDao.findById(tUserCourseStudyPo.getCourseId()).get();
                TCourseSectionPo tCourseSectionPo = courseSectionDao.findById(tUserCourseStudyPo.getSectionId()).get();
                CourseVo courseVo = PoConvertVo.convert(tCoursePo);
                UserCourseVo userCourseVo = UserCourseVo.builder()
                        .id(i+1)
                        .courseVo(courseVo)
                        .sectionId(tCourseSectionPo.getId())
                        .sectionName(tCourseSectionPo.getSectionName())
                        .firstStudyTime(OceanDateUtil.converDate(tUserCourseStudyPo.getFirstStudyTime().getTime()))
                        .lastStudyTime(OceanDateUtil.converDate(tUserCourseStudyPo.getLastStudyTime().getTime()))
                        .build();
                userCourseVoList.add(userCourseVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("UserCourseBusinessImpl getMyCoursesByUserId()我的课程数据转换失败!");
        }
        return userCourseVoList;
    }

}
