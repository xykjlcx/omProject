package com.xykj.omapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.xykj.omapp.business.impl.CourseBusinessImpl;
import com.xykj.omapp.utils.PoConvertVo;
import com.xykj.omapp.vo.CourseCommentVo;
import com.xykj.omapp.vo.CourseVo;
import com.xykj.omapp.vo.TestVo;
import com.xykj.ombase.returnformat.OceanReturn;
import com.xykj.ombase.returnformat.Result;
import com.xykj.omservice.course.po.TCourseClassifyPo;
import com.xykj.omservice.course.po.TCourseCommentPo;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.services.impl.CourseCommentServiceImpl;
import com.xykj.omservice.course.services.impl.CourseServiceImpl;
import com.xykj.omservice.course.services.impl.CouseClassifyServiceImpl;
import com.xykj.omservice.user.services.impl.UserCourseStudyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    CourseBusinessImpl courseBusiness;
    @Autowired
    CourseCommentServiceImpl courseCommentService;
    @Autowired
    UserCourseStudyServiceImpl userCourseStudyService;

    @RequestMapping(value = "/getAllCourseForPage", method = RequestMethod.POST)
    public Result getAllCourseForPage(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("classify") int classify
    ) {
        try {
            System.out.println("page:" + page + ",size:" + size);
            Pageable pageable = new PageRequest(page, size);
            Page<TCoursePo> coursePoPage = courseService.findByClassifyIdAndPage(classify,pageable);
            List<TCoursePo> coursePoList = coursePoPage.getContent();
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

    /**
     * 获取课程章节
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/getCourseChapterAndSection",method = RequestMethod.POST)
    public Result getCourseChapterAndSection(@RequestParam("courseId") int courseId){
        try {
            Map<String,Object> data = courseBusiness.getChapterAndSection(courseId);
            return OceanReturn.successResult(
                    "获取课程章节列表成功",
                    data
            );
        } catch (Exception e) {
            e.printStackTrace();
            return OceanReturn.errorResult(
                    "获取课程章节列表失败",
                    null
            );
        }
    }

    @RequestMapping(value = "/getCourseComments",method = RequestMethod.POST)
    public Result getCourseComments(@RequestParam("courseId") int courseId,
                                    @RequestParam("page") int page,
                                    @RequestParam("size") int size){
        List<CourseCommentVo> courseCommentVoList = null;
        try {
            Pageable pageable = new PageRequest(page,size);
            courseCommentVoList = courseBusiness.getCourseCommentsByCourseIdForPage(courseId,pageable);
            return OceanReturn.successResult(
                    "获取评论列表成功",
                    courseCommentVoList
            );
        } catch (RuntimeException e) {
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }
    }

    /**
     * 添加评论接口
     * @param userId
     * @param courseId
     * @param sectionId
     * @param content
     * @return
     */
    @RequestMapping(value = "/addComment",method = RequestMethod.POST)
    public Result addComment(@RequestParam("userId") int userId,
                             @RequestParam("courseId") int courseId,
                             @RequestParam("sectionId") int sectionId,
                             @RequestParam("content") String content){
        try {
            TCourseCommentPo courseCommentPo = TCourseCommentPo.builder()
                    .userId(userId)
                    .courseId(courseId)
                    .sectionId(sectionId)
                    .commentContent(content)
                    .build();
            courseCommentService.addComment(courseCommentPo);
            return OceanReturn.successResult(
                    "新增评论成功",
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
                    "评论失败，未知错误",
                    null
            );
        }
    }

    @RequestMapping(value = "/searchCourses",method = RequestMethod.POST)
    public Result searchCourses(@RequestParam("courseName") String courseName,
                                @RequestParam("page") int page,
                                @RequestParam("size") int size){
        try {
            Pageable pageable = new PageRequest(page,size);
            List<TCoursePo> coursePoList = courseService.searchCourseByNameForPage(courseName,pageable);
            List<CourseVo> resultCourseVoList = new ArrayList<>();
            coursePoList.forEach(tCoursePo -> {
                CourseVo courseVo = PoConvertVo.convert(tCoursePo);
                int count = userCourseStudyService.getCourseStudyCount(courseVo.getId());
                courseVo.setCount(count);
                resultCourseVoList.add(courseVo);
            });
            return OceanReturn.successResult(
                    "搜索课程成功",
                    resultCourseVoList
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }
    }

    @RequestMapping(value = "/getCourseStudyCount",method = RequestMethod.POST)
    public Result getCourseStudyCount(@RequestParam("courseId") int courseId){
        try {
            int studyCount = userCourseStudyService.getCourseStudyCount(courseId);
            return OceanReturn.successResult(
                    "查询学习人数成功",
                    studyCount
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }
    }


//    @RequestMapping(value = "/testjass",method = RequestMethod.POST)
//    public Result testjass(@RequestBody JSONObject jsonObject){
//        String str = jsonObject.toString();
//        TestVo testVo = new Gson().fromJson(str,TestVo.class);
//        int page = testVo.getPage();
//        int size = testVo.getSize();
//        int classify = testVo.getClassify();
//        try {
//            System.out.println("page:" + page + ",size:" + size);
//            Pageable pageable = new PageRequest(page, size);
//            List<TCoursePo> coursePoList = courseService.findByClassifyIdAndPage(classify,pageable);
//            List<CourseVo> courseVoList = new ArrayList<>();
//            coursePoList.forEach(tCoursePo -> {
//                CourseVo courseVo = PoConvertVo.convert(tCoursePo);
//                int count = userCourseStudyService.getCourseStudyCount(courseVo.getId());
//                courseVo.setCount(count);
//                courseVoList.add(courseVo);
//            });
//            return OceanReturn.successResult(
//                    "获取课程数据成功,当前页:" + page + ",每页显示：" + size,
//                    courseVoList
//            );
//        } catch (Exception e) {
//            e.printStackTrace();
//            return OceanReturn.errorResult(
//                    "获取课程数据失败，当前页：" + page + ",每页显示：" + size,
//                    null
//            );
//        }
//    }

//    @RequestMapping(value = "/test888",method = RequestMethod.POST)
//    public void test888(@RequestParam int page){
//        System.out.println(new Gson().fromJson(page,TestVo.class).);
//    }

}
