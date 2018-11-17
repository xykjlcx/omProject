package com.xykj.omapp.controller;

import com.xykj.omapp.utils.PoConvertVo;
import com.xykj.omapp.vo.CourseVo;
import com.xykj.ombase.returnformat.OceanReturn;
import com.xykj.ombase.returnformat.Result;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.services.impl.CourseServiceImpl;
import com.xykj.omservice.home.services.impl.HomeBannerService;
import com.xykj.omservice.home.dao.NoticesDao;
import com.xykj.omservice.home.po.TNoticesPo;
import com.xykj.omservice.user.services.impl.UserCourseStudyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    HomeBannerService homeBannerService;

    @Autowired
    CourseServiceImpl courseService;

    @Autowired
    UserCourseStudyServiceImpl userCourseStudyService;

    @Autowired
    NoticesDao noticesDao;

    /**
     * 获取banner轮播推荐
     * @return
     */
    @RequestMapping(value = "/getBannerData",method = RequestMethod.GET)
    public Result getBannerData(){
        try {
            List<TCoursePo> tCoursePoList = homeBannerService.findAll();
            List<CourseVo> courseVoList = new ArrayList<>();
            tCoursePoList.forEach(tCoursePo -> {
                CourseVo courseVo = PoConvertVo.convert(tCoursePo);
                int count = userCourseStudyService.getCourseStudyCount(courseVo.getId());
                courseVo.setCount(count);
                courseVoList.add(courseVo);
            });
            return OceanReturn.successResult(
                    "获取课程轮播成功",
                    courseVoList
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }catch (Exception e){
            return OceanReturn.errorResult(
                    "获取轮播失败",
                    null
            );
        }
    }

    /**
     * 获取首页公告
     * @return
     */
    @RequestMapping(value = "/getNotice",method = RequestMethod.GET)
    public Result getNotice(){
        List<TNoticesPo> list = noticesDao.findAll();
        return OceanReturn.successResult(
                "获取首页公告成功",
                list
        );
    }


    /**
     * 获取精选课程(权值最高的课程)
     * 4个
     * @return
     */
    @RequestMapping(value = "/getRecommendCourse",method = RequestMethod.GET)
    public Result getRecommendCourse(){
       try {
           Sort sort = new Sort(Sort.Direction.DESC,"weight");
           Pageable pageable = new PageRequest(0,4,sort);
           List<TCoursePo> coursePoList = courseService.findAllByPage(pageable);
           List<CourseVo> courseVoList = new ArrayList<>();
           coursePoList.forEach(tCoursePo -> {
               CourseVo courseVo = PoConvertVo.convert(tCoursePo);
               int count = userCourseStudyService.getCourseStudyCount(courseVo.getId());
               courseVo.setCount(count);
               courseVoList.add(courseVo);
           });
           return OceanReturn.successResult(
                   "获取推荐好课成功",
                   courseVoList
           );
       }catch (Exception e){
           e.printStackTrace();
           return OceanReturn.errorResult(
                   "获取推荐好课失败",
                   null
           );
       }
    }


    /**
     * 猜你喜欢(默认首页列表,分页)
     * @return
     */
    @RequestMapping(value = "/getGuessLikeCourse",method = RequestMethod.POST)
    public Result getGuessLikeCourse(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        try {
            System.out.println("page:" + page + ",size:" + size);
            Pageable pageable = new PageRequest(page,size);
            List<TCoursePo> coursePoList = courseService.findAllByPage(pageable);
            List<CourseVo> courseVoList = new ArrayList<>();
            coursePoList.forEach(tCoursePo -> {
                CourseVo courseVo = PoConvertVo.convert(tCoursePo);
                int count = userCourseStudyService.getCourseStudyCount(courseVo.getId());
                courseVo.setCount(count);
                courseVoList.add(courseVo);
            });
            return OceanReturn.successResult(
                    "获取课程数据成功,当前页:" + page + ",每页显示：" + size,
                    courseVoList
            );
        } catch (Exception e) {
            e.printStackTrace();
            return OceanReturn.errorResult(
                    "获取课程数据失败，当前页：" + page + ",每页显示：" + size,
                    null
            );
        }
    }



}
