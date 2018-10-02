package com.xykj.omapp.controller;

import com.xykj.omapp.utils.PoConvertVo;
import com.xykj.omapp.vo.CourseVo;
import com.xykj.ombase.returnformat.OceanReturn;
import com.xykj.ombase.returnformat.Result;
import com.xykj.omservice.course.dao.CourseClassifyDao;
import com.xykj.omservice.course.po.TCourseClassifyPo;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.services.impl.CourseServiceImpl;
import com.xykj.omservice.course.services.impl.CouseClassifyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ocean
 * @Title: CourseController
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/9/24下午3:33
 */
@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;
    @Autowired
    CouseClassifyServiceImpl couseClassifyService;


    @RequestMapping(value = "/getAllCourseForPage", method = RequestMethod.POST)
    public Result getAllCourseForPage(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("classify") int classify
    ) {
        try {
//            System.out.println("page:" + page + ",size:" + size);
            Pageable pageable = new PageRequest(page, size);
            List<TCoursePo> coursePoList = courseService.findByClassifyIdAndPage(classify,pageable);
            List<CourseVo> courseVoList = new ArrayList<>();
            coursePoList.forEach(tCoursePo -> courseVoList.add(PoConvertVo.convert(tCoursePo)));
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

    @RequestMapping(value = "/getAllClassify",method = RequestMethod.GET)
    public Result getAllClassify(){
       try {
           /*一级分类*/
           List<TCourseClassifyPo> levelOneClassifyList = couseClassifyService.findAnyLevel(0);
           /*二级分类*/
           List<List<TCourseClassifyPo>> levelTwoClassifyList = new ArrayList<>();
           for (int i = 0; i < levelOneClassifyList.size(); i++) {
               int parentId = levelOneClassifyList.get(i).getId();
               List<TCourseClassifyPo> temp = couseClassifyService.findAnyLevel(parentId);
               levelTwoClassifyList.add(temp);
           }
           Map<String,Object> data = new HashMap<>();
           data.put("oneLevel",levelOneClassifyList);
           data.put("twoLevel",levelTwoClassifyList);
           return OceanReturn.successResult(
                   "获取所有分类成功",
                   data
           );
       }catch (Exception e){
           e.printStackTrace();
           return OceanReturn.errorResult(
                   "获取所有分类失败",
                   null
           );
       }
    }


}
