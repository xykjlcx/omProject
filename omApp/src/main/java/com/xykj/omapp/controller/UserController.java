package com.xykj.omapp.controller;

import com.xykj.omapp.business.impl.UserCourseBusinessImpl;
import com.xykj.omapp.vo.UserCourseVo;
import com.xykj.ombase.returnformat.OceanReturn;
import com.xykj.ombase.returnformat.Result;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.user.dao.UserDao;
import com.xykj.omservice.user.po.TUserCourseStudyPo;
import com.xykj.omservice.user.services.impl.UserCourseStudyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ocean
 * @Title: UserController
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/1下午1:19
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserCourseBusinessImpl userCourseBusiness;
    @Autowired
    UserCourseStudyServiceImpl userCourseStudyService;

    /**
     * 获取用户(我的课程)学习课程
     * @return
     */
    @RequestMapping(value = "/getStudyCourses",method = RequestMethod.POST)
    public Result getStudyCourses(
            @RequestParam("userId") int userId
    ){
        try {
            List<UserCourseVo> userCourseVoList = userCourseBusiness.getMyCoursesByUserId(userId);
            return OceanReturn.successResult(
                    "获取我的课程成功",
                    userCourseVoList
            );
        } catch (Exception e) {
            e.printStackTrace();
            return OceanReturn.errorResult(
                    "获取我的课程失败",
                    null
            );
        }
    }

    @RequestMapping(value = "/addStudyCourse",method = RequestMethod.POST)
    public Result addStudyCourse(
            // 传参
            @RequestParam("userId") int userId,
            @RequestParam("courseId") int courseId,
            // sectionId == 0时，为首次学习该课程
            @RequestParam("sectionId") int sectionId
    ){
        try {
            TUserCourseStudyPo studyPo = TUserCourseStudyPo.builder()
                    .userId(userId)
                    .courseId(courseId)
                    .sectionId(sectionId)
                    .build();
            userCourseStudyService.save(studyPo);
            return OceanReturn.successResult(
                    "用户：" + userId + "已添加课程：" + courseId,
                    null
            );
        }catch (Exception e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    "用户：" + userId + "添加课程：" + courseId + "失败",
                    null
            );
        }
    }

    @RequestMapping(value = "/isStudyCourse",method = RequestMethod.POST)
    public Result isStudyCourse(
            @RequestParam("userId") int userId,
            @RequestParam("courseId") int courseId
    ){
        try {
            boolean isStuied = userCourseStudyService.isStudyCourse(userId,courseId);
            return OceanReturn.successResult(
                    "判断成功",
                    isStuied
            );
        }catch (Exception e){
            return OceanReturn.errorResult(
                    "判断失败",
                    null
            );
        }
    }

}
