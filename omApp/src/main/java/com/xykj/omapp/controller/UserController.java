package com.xykj.omapp.controller;

import com.xykj.omapp.business.impl.UserCourseBusinessImpl;
import com.xykj.omapp.vo.UserCourseVo;
import com.xykj.ombase.returnformat.OceanReturn;
import com.xykj.ombase.returnformat.Result;
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

}
