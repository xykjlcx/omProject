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
import org.springframework.data.domain.Sort;
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
        int page = 0,size = 0,classify = 0,sort = 0;
        String sortProp;
        try {
            page = jsonObject.getInteger("page");
            size = jsonObject.getInteger("size");
            classify = jsonObject.getInteger("classify");
            sort = jsonObject.getInteger("sort");       // 0为正序，1位倒序
            sortProp = jsonObject.getString("sortProp"); // 以什么字段排序
            System.out.println("page:" + page + ",size:" + size);
            Pageable pageable = null;
            if (sortProp.equals("none")){
                sortProp = "id";
            }
            if (sort == 0){
                // 默认为0 为正序
                Sort orders1 = new Sort(Sort.Direction.ASC,sortProp);
                pageable = new PageRequest(page,size,orders1);
            }else {
                // 倒序
                Sort orders2 = new Sort(Sort.Direction.DESC,sortProp);
                pageable = new PageRequest(page, size,orders2);
            }
            List<TCoursePo> coursePoList = courseService.findByClassifyIdAndPage(classify,pageable);
            List<CourseVoAdmin> courseVoList = new ArrayList<>();
            for (int i = 0; i < coursePoList.size(); i++) {
                TCoursePo tCoursePo = coursePoList.get(i);
                CourseVoAdmin courseVo = PoConvertVo.convert(tCoursePo,page*size + (i+1));
                int count = userCourseStudyService.getCourseStudyCount(tCoursePo.getId());
                courseVo.setStudyCount(count);
                courseVoList.add(courseVo);
            }
            Map<String,Object> data = new HashMap<>();
            data.put("courseList",courseVoList);
            data.put("count",courseService.getCourseCount());
            return OceanReturn.successResult(
                    "获取课程数据成功,当前页:" + page + ",每页显示：" + size,
                    data
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


    @RequestMapping(value = "/searchCourses",method = RequestMethod.POST)
    public Result searchCourses(@RequestBody JSONObject jsonObject){
        String courseName = "";
        int page = 0;
        int size = 0;
        int sort = 0;
        String sortProp = "";
        try {
            sort = jsonObject.getInteger("sort");       // 0为正序，1位倒序
            sortProp = jsonObject.getString("sortProp"); // 以什么字段排序
            courseName = jsonObject.getString("courseName");
            page = jsonObject.getInteger("page");
            size = jsonObject.getInteger("size");

            Pageable pageable = null;
            if (sortProp.equals("none")){
                sortProp = "id";
            }
            if (sort == 0){
                // 默认为0 为正序
                Sort orders1 = new Sort(Sort.Direction.ASC,sortProp);
                pageable = new PageRequest(page,size,orders1);
            }else {
                // 倒序
                Sort orders2 = new Sort(Sort.Direction.DESC,sortProp);
                pageable = new PageRequest(page, size,orders2);
            }
            List<TCoursePo> coursePoList = courseService.searchCourseByNameForPage(courseName,pageable);
            List<CourseVoAdmin> resultCourseVoList = new ArrayList<>();
            for (int i = 0; i < coursePoList.size(); i++) {
                TCoursePo tCoursePo = coursePoList.get(i);
                CourseVoAdmin courseVo = PoConvertVo.convert(tCoursePo,page*size + (i+1));
                int count = userCourseStudyService.getCourseStudyCount(courseVo.getId());
                courseVo.setStudyCount(count);
                resultCourseVoList.add(courseVo);
            }
            Map<String,Object> data = new HashMap<>();
            data.put("courseList",resultCourseVoList);
            data.put("count",coursePoList.size());
            return OceanReturn.successResult(
                    "搜索课程成功",
                    data
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }
    }


}
