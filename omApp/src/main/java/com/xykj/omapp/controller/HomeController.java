package com.xykj.omapp.controller;

import com.xykj.omapp.utils.PoConvertVo;
import com.xykj.omapp.vo.CourseVo;
import com.xykj.ombase.returnformat.OceanReturn;
import com.xykj.ombase.returnformat.Result;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.services.impl.CourseServiceImpl;
import com.xykj.omservice.home.services.impl.HomeBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/getBannerData",method = RequestMethod.GET)
    public Result getBannerData(){
        try {
            return OceanReturn.successResult(
                    "获取课程轮播成功",
                    homeBannerService.findAll()
            );
        }catch (Exception e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    "获取课程轮播失败",
                    null
            );
        }
    }


    /**
     * 获取推荐好课(权值最高的课程)
     * @return
     */
    @RequestMapping(value = "/getRecommendCourse",method = RequestMethod.GET)
    public Result getRecommendCourse(){
       try {
           Sort sort = new Sort(Sort.Direction.DESC,"weight");
           Pageable pageable = new PageRequest(0,5,sort);
           List<TCoursePo> coursePoList = courseService.findAllByPage(pageable);
           List<CourseVo> courseVoList = new ArrayList<>();
           coursePoList.forEach(tCoursePo -> {
               courseVoList.add(PoConvertVo.convert(tCoursePo));
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
                courseVoList.add(PoConvertVo.convert(tCoursePo));
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


    private String[] noticesStrs = {
            "实现三栏布局的几种方法",
            "Java集合框架",
            "深入理解JAVA虚拟机",
            "TensorFlow入门",
            "Spark流式处理"
    };

    /**
     * 获取首页公告
     * @return
     */
    @RequestMapping("/getNotice")
    public Result getNotice(){
        List<Map<String,Object>> notices = new ArrayList<>();
        for (int i = 0; i < noticesStrs.length; i++) {
            Map<String,Object> item = new HashMap<>();
            item.put("newsKey",noticesStrs[i]);
            notices.add(item);
        }
        return OceanReturn.successResult(
                "获取首页公告成功",
                notices
        );
    }

}
