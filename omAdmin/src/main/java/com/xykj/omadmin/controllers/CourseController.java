package com.xykj.omadmin.controllers;

import com.alibaba.fastjson.JSONObject;
import com.xykj.omadmin.utils.PoConvertVo;
import com.xykj.omadmin.utils.VoConvertPo;
import com.xykj.omadmin.vo.CourseClassifyVoAdmin;
import com.xykj.omadmin.vo.CourseVoAdmin;
import com.xykj.ombase.returnformat.OceanReturn;
import com.xykj.ombase.returnformat.Result;
import com.xykj.omservice.course.po.TCourseClassifyPo;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.services.impl.CourseServiceImpl;
import com.xykj.omservice.course.services.impl.CouseClassifyServiceImpl;
import com.xykj.omservice.user.services.impl.UserCourseStudyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
 * @date 2018/10/13下午5:53
 */
@RestController
@RequestMapping("/admin/courses")
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;
    @Autowired
    UserCourseStudyServiceImpl userCourseStudyService;
    @Autowired
    CouseClassifyServiceImpl couseClassifyService;

    /**
     * 获取所有课程(分页)
     * 增加顺序id字段
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/getAllCourses",method = RequestMethod.POST)
    public Result getAllCourses(@RequestBody JSONObject jsonObject){
        int page = jsonObject.getInteger("page");
        int size = jsonObject.getInteger("size");
        int classify = jsonObject.getInteger("classify");
        try {
            System.out.println("page:" + page + ",size:" + size);
            Pageable pageable = new PageRequest(page, size);
            List<TCoursePo> coursePoList = courseService.findByClassifyIdAndPage(classify,pageable);
            List<CourseVoAdmin> courseVoList = new ArrayList<>();
            for (int i = 0; i < coursePoList.size(); i++) {
                TCoursePo tCoursePo = coursePoList.get(i);
                CourseVoAdmin courseVo = PoConvertVo.convert(tCoursePo,page*size + (i+1));
                int count = userCourseStudyService.getCourseStudyCount(courseVo.getId());
                courseVo.setCount(count);
                courseVoList.add(courseVo);
            }
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
        }    }

    /**
     * 添加新的课程
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/addCourse",method = RequestMethod.POST)
    public Result addCourse(@RequestBody JSONObject jsonObject){
        try {
            TCoursePo newCoursePo = VoConvertPo.convert(jsonObject);
            courseService.addNewCourse(newCoursePo);
            return OceanReturn.successResult(
                    "添加课程成功",
                    null
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    "添加课程失败" + e.getMessage(),
                    null
            );
        }
    }

    /**
     * 修改课程信息
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/editCourses",method = RequestMethod.POST)
    public Result editCourses(@RequestBody JSONObject jsonObject){
        try {
            TCoursePo newCoursePo = VoConvertPo.convert(jsonObject);
            courseService.editCourse(newCoursePo);
            return OceanReturn.successResult(
                    "修改课程信息成功",
                    null
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    "修改课程信息失败" + e.getMessage(),
                    null
            );
        }
    }

    /**
     * 获取课程分类
     * @return
     */
    @RequestMapping(value = "/getAllClassify",method = RequestMethod.GET)
    public Result getAllClassify(){
        List<CourseClassifyVoAdmin> resultOneList = new ArrayList<>();
        List<List<CourseClassifyVoAdmin>> resultTwoList = new ArrayList<>();
        try {
            /*一级分类*/
            List<TCourseClassifyPo> levelOneClassifyList = couseClassifyService.findAnyLevel(0);
            /*二级分类*/
            List<List<TCourseClassifyPo>> levelTwoClassifyList = new ArrayList<>();
            for (int i = 0; i < levelOneClassifyList.size(); i++) {
                TCourseClassifyPo tCourseClassifyPo = levelOneClassifyList.get(i);
                resultOneList.add(PoConvertVo.convert(tCourseClassifyPo,(i+1)));
                int parentId = tCourseClassifyPo.getId();
                List<TCourseClassifyPo> tempTpo = couseClassifyService.findAnyLevel(parentId);
                List<CourseClassifyVoAdmin> tempVo = new ArrayList<>();
                for (int i1 = 0; i1 < tempTpo.size(); i1++) {
                    tempVo.add(PoConvertVo.convert(tempTpo.get(i1),(i1+1)));
                }
                resultTwoList.add(tempVo);
            }
            Map<String,Object> data = new HashMap<>();
            data.put("oneLevel",resultOneList);
            data.put("twoLevel",resultTwoList);
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
