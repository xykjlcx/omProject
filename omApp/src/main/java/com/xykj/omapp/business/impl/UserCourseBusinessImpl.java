package com.xykj.omapp.business.impl;

import com.xykj.omapp.business.IUserCourseBusiness;
import com.xykj.omapp.utils.PoConvertVo;
import com.xykj.omapp.vo.CourseVo;
import com.xykj.omapp.vo.MyCommentVo;
import com.xykj.omapp.vo.UserCourseVo;
import com.xykj.ombase.utils.OceanDateUtil;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.course.dao.CourseSectionDao;
import com.xykj.omservice.course.po.TCourseCommentPo;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.po.TCourseSectionPo;
import com.xykj.omservice.course.services.impl.CourseCommentServiceImpl;
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
    @Autowired
    CourseCommentServiceImpl courseCommentService;

    @Override
    public List<UserCourseVo> getMyCoursesByUserId(int userId) throws RuntimeException{
        List<UserCourseVo> userCourseVoList = new ArrayList<>();
        try {
            List<TUserCourseStudyPo> userCourseStudyPoList = userCourseStudyService.getMyStudyCourseByUserId(userId);
            for (int i = 0; i < userCourseStudyPoList.size(); i++) {
                TUserCourseStudyPo tUserCourseStudyPo = userCourseStudyPoList.get(i);
                TCoursePo tCoursePo = courseDao.findById(tUserCourseStudyPo.getCourseId()).get();
                TCourseSectionPo tCourseSectionPo = new TCourseSectionPo();
                if (tUserCourseStudyPo.getSectionId() == -1){
                    // todo 仅仅是进入课程点击了开始学习，并未进行具体章节的学习
                    // 赋默认值
                    tCourseSectionPo.setId(-1);
                    tCourseSectionPo.setSectionName("课程概览，待进行具体章节学习");
                }else {
                    tCourseSectionPo = courseSectionDao.findById(tUserCourseStudyPo.getSectionId()).get();
                }
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
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
        return userCourseVoList;
    }

    @Override
    public List<MyCommentVo> getMyCommentsByUserId(int userId) throws RuntimeException {
        List<MyCommentVo> resultMyCommentVoList = new ArrayList<>();
        try {
            List<TCourseCommentPo> courseCommentPoList = courseCommentService.findAllByUserId(userId);
            courseCommentPoList.forEach(tCourseCommentPo -> {
                CourseVo courseVo = PoConvertVo.convert(courseDao.findAllById(tCourseCommentPo.getCourseId()).get(0));
                resultMyCommentVoList.add(PoConvertVo.convert(tCourseCommentPo,courseVo));
            });
            return resultMyCommentVoList;
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("获取我的评论，未知异常");
        }
    }

}
