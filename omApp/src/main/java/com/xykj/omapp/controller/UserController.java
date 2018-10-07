package com.xykj.omapp.controller;

import com.xykj.omapp.business.impl.UserBusinessImpl;
import com.xykj.omapp.business.impl.UserCourseBusinessImpl;
import com.xykj.omapp.utils.PoConvertVo;
import com.xykj.omapp.vo.CourseVo;
import com.xykj.omapp.vo.MyCommentVo;
import com.xykj.omapp.vo.UserCourseVo;
import com.xykj.ombase.returnformat.OceanReturn;
import com.xykj.ombase.returnformat.Result;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.user.dao.UserDao;
import com.xykj.omservice.user.po.TIdeaBackPo;
import com.xykj.omservice.user.po.TUserCourseStudyPo;
import com.xykj.omservice.user.services.impl.IdeaBackServiceImpl;
import com.xykj.omservice.user.services.impl.UserCourseCollectServiceImpl;
import com.xykj.omservice.user.services.impl.UserCourseStudyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
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
    @Autowired
    UserCourseCollectServiceImpl userCourseCollectService;
    @Autowired
    UserBusinessImpl userBusiness;

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
        } catch (RuntimeException e) {
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
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

    /**
     * 是否学习过该课程
     * @param userId
     * @param courseId
     * @return
     */
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

    /**
     * 添加课程收藏
     * @param userId
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/addCollectCourse",method = RequestMethod.POST)
    public Result addCollectCourse(@RequestParam("userId") int userId,
                                   @RequestParam("courseId") int courseId){
        // 添加课程收藏
        try {
            userCourseCollectService.addCollectCourses(userId,courseId);
            return OceanReturn.successResult(
                    "收藏成功",
                    null
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }catch (Exception e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    "收藏失败，未知错误",
                    null
            );
        }
    }

    /**
     * 判断是否收藏过该课程
     * @return
     */
    @RequestMapping(value = "/isCollect",method = RequestMethod.POST)
    public Result isCollect(@RequestParam("userId") int userId,
                            @RequestParam("courseId") int courseId){
        try {
            boolean isExist = userCourseCollectService.isCollectCourse(userId,courseId);
            return OceanReturn.successResult(
                    "判断成功",
                    isExist
            );
        }catch (Exception e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    "判断失败",
                    null
            );
        }
    }

    /**
     * 获取我的收藏列表
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getCollectCourses",method = RequestMethod.POST)
    public Result getCollectCourses(@RequestParam("userId") int userId){
        try {
            List<TCoursePo> tCoursePoList = userCourseCollectService.queryCollectCourseByUserId(userId);
            List<CourseVo> resultCourseList = new ArrayList<>();
            tCoursePoList.forEach(tCoursePo -> {
                resultCourseList.add(PoConvertVo.convert(tCoursePo));
            });
            return OceanReturn.successResult(
                    "查询我的收藏成功",
                    resultCourseList
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }catch (Exception e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    "查询我的收藏失败,未知错误",
                    null
            );
        }
    }

    /**
     * 取消收藏记录
     * @param userId
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/deleteCollectCourse",method = RequestMethod.POST)
    public Result deleteCollectCourse(@RequestParam("userId") int userId,
                                      @RequestParam("courseId") int courseId){
        try {
            userCourseCollectService.deleteCollectCourse(userId,courseId);
            return OceanReturn.successResult(
                    "取消收藏成功",
                    null
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }catch (Exception e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    "取消收藏失败，未知错误",
                    null
            );
        }
    }

    @RequestMapping(value = "/getMyComments",method = RequestMethod.POST)
    public Result getMyComments(@RequestParam("userId") int userId){
        try {
            List<MyCommentVo> myCommentVoList = userCourseBusiness.getMyCommentsByUserId(userId);
            return OceanReturn.successResult(
                    "获取我的评论列表成功",
                    myCommentVoList
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }
    }

    @RequestMapping(value = "/addIdeaBack",method = RequestMethod.POST)
    public Result addIdeaBack(@RequestParam("userId") int userId,
                              @RequestParam("address") String address,
                              @RequestParam("contact") String contact,
                              @RequestParam("content") String content){
       try {
           TIdeaBackPo ideaBackPo = TIdeaBackPo.builder()
                   .address(address)
                   .contact(contact)
                   .content(content)
                   .userId(userId)
                   .createTime(new Timestamp(System.currentTimeMillis()))
                   .build();
           userBusiness.addIdeaBack(ideaBackPo);
           return OceanReturn.successResult(
                   "意见提交成功",
                   null
           );
       }catch (Exception e){
           e.printStackTrace();
           return OceanReturn.errorResult(
                   "意见提交失败",
                   null
           );
       }

    }

}
