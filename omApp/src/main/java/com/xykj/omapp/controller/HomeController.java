package com.xykj.omapp.controller;

import com.xykj.ombase.returnformat.OceanReturn;
import com.xykj.ombase.returnformat.Result;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.services.impl.CourseServiceImpl;
import com.xykj.omservice.home.services.impl.HomeBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    HomeBannerService homeBannerService;

    @Autowired
    CourseServiceImpl courseService;

    /**
     * 获取banner轮播推荐
     * @return
     */
    @RequestMapping("/getBannerData")
    public Result getBannerData(){
        return null;
    }


    /**
     * 获取推荐好课
     * @return
     */
    @RequestMapping("/getRecommendCourse")
    public Result getRecommendCourse(){
        return null;
    }


    /**
     * 猜你喜欢
     * @return
     */
    @RequestMapping("/getGuessLikeCourse")
    public Result getGuessLikeCourse(){
        return null;
    }


    /**
     * 获取首页公告
     * @return
     */
    @RequestMapping("/getNotice")
    public Result getNotice(){
        List<String> notices = new ArrayList<>();
        notices.add("Ocean Mooc开放使用!!!");
        notices.add("云溪大会在杭州召开");
        notices.add("Google IO 2018开始助推跨平台技术：Flutter");

        return OceanReturn.successResult(
                "获取首页公告成功",
                notices
        );
    }


    @RequestMapping("/test")
    public Result test(){
        List<TCoursePo> list = null;
        try {
            list = courseService.findAllByWeightPage(0,5);
        }catch (Exception e){
            e.printStackTrace();
        }
        return OceanReturn.successResult(
                "测试注入",
                list
        );
    }

}
