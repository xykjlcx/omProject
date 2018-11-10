package com.xykj.omadmin.controllers;

import com.alibaba.fastjson.JSONObject;
import com.xykj.omadmin.business.impl.CourseBusinessImpl;
import com.xykj.omadmin.utils.PoConvertVo;
import com.xykj.omadmin.utils.VoConvertPo;
import com.xykj.omadmin.vo.CourseClassifyVoAdmin;
import com.xykj.omadmin.vo.CourseSectionVo;
import com.xykj.omadmin.vo.CourseVoAdmin;
import com.xykj.ombase.returnformat.OceanReturn;
import com.xykj.ombase.returnformat.Result;
import com.xykj.ombase.utils.OceanDateUtil;
import com.xykj.omservice.course.po.TCourseClassifyPo;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.services.ICourseCommentService;
import com.xykj.omservice.course.services.impl.CourseSectionServiceImpl;
import com.xykj.omservice.course.services.impl.CourseServiceImpl;
import com.xykj.omservice.course.services.impl.CouseClassifyServiceImpl;
import com.xykj.omservice.home.services.impl.HomeBannerService;
import com.xykj.omservice.user.services.impl.UserCourseStudyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Autowired
    HomeBannerService homeBannerService;
    @Autowired
    CourseBusinessImpl courseBusiness;
    @Autowired
    CourseSectionServiceImpl courseSectionService;
    @Autowired
    ICourseCommentService courseCommentService;

    /**
     * 获取所有课程(分页)
     * 增加顺序id字段
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/getAllCourses",method = RequestMethod.POST)
    public Result getAllCourses(@RequestBody JSONObject jsonObject){
        long firstTime = OceanDateUtil.getCurrentTime();
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
            Page<TCoursePo> coursePoPage = courseService.findByClassifyIdAndPage(classify,pageable);
            List<TCoursePo> coursePoList = coursePoPage.getContent();
            List<CourseVoAdmin> courseVoList = new ArrayList<>();
            for (int i = 0; i < coursePoList.size(); i++) {
                TCoursePo tCoursePo = coursePoList.get(i);
                CourseVoAdmin courseVo = PoConvertVo.convert(tCoursePo,page*size + (i+1));
                int count = userCourseStudyService.getCourseStudyCount(courseVo.getDbId());
                // 设置是否已经推荐至首页轮播
                courseVo.setHomeBannerS(homeBannerService.isCourseBelongToBanner(courseVo.getDbId()));
                courseVo.setStudyCount(count);
                courseVoList.add(courseVo);
            }
            Map<String,Object> data = new HashMap<>();
            data.put("courseList",courseVoList);
            data.put("count",coursePoPage.getTotalElements());
            long endTime = OceanDateUtil.getCurrentTime();
            System.out.println("查询所有课程的用时：" + (endTime - firstTime));
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
        }
    }


    /**
     * 获取一条课程信息
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/getCourseOneById",method = RequestMethod.POST)
    public Result getCourseOneById(@RequestBody JSONObject jsonObject){
        int courseId = 0;
        try {
            courseId = jsonObject.getInteger("courseId");
            TCoursePo tCoursePo = courseService.getCourseInfoById(courseId);
            CourseVoAdmin courseVo = PoConvertVo.convert(tCoursePo,1);
            int count = userCourseStudyService.getCourseStudyCount(courseVo.getDbId());
            // 设置是否已经推荐至首页轮播
            courseVo.setHomeBannerS(homeBannerService.isCourseBelongToBanner(courseVo.getDbId()));
            courseVo.setStudyCount(count);
            return OceanReturn.successResult(
                    "获取课程信息成功",
                    courseVo
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }
    }

    /**
     * 添加/编辑的课程
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/addAndEditCourse",method = RequestMethod.POST)
    public Result addAndEditCourse(@RequestBody JSONObject jsonObject){
        try {
            TCoursePo tCoursePo = VoConvertPo.convert(jsonObject);
            Integer id = jsonObject.getInteger("dbId");
            if (id != null){
                tCoursePo.setId(id);
                courseService.editCourse(tCoursePo);
            }else {
                courseService.addNewCourse(tCoursePo);
            }
            return OceanReturn.successResult(
                    "操作成功",
                    null
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    "操作失败" + e.getMessage(),
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

    @RequestMapping(value = "/getAllClassifyTree",method = RequestMethod.GET)
    public Result getAllClassifyTree(){
        List<CourseClassifyVoAdmin> dataList = new ArrayList<>();
        try {
            /*一级分类*/
            List<TCourseClassifyPo> levelOneClassifyList = couseClassifyService.findAnyLevel(0);
            for (int i = 0; i < levelOneClassifyList.size(); i++) {
                TCourseClassifyPo tCourseClassifyPo = levelOneClassifyList.get(i);
                CourseClassifyVoAdmin courseClassifyVoAdmin = PoConvertVo.convert(tCourseClassifyPo,i+1);
                int parentId = tCourseClassifyPo.getId();
                List<TCourseClassifyPo> tempChildList = couseClassifyService.findAnyLevel(parentId);
                List<CourseClassifyVoAdmin> tempVo = new ArrayList<>();
                for (int i1 = 0; i1 < tempChildList.size(); i1++) {
                    tempVo.add(PoConvertVo.convert(tempChildList.get(i1),(i1+1)));
                }
                courseClassifyVoAdmin.setChilds(tempVo);
                dataList.add(courseClassifyVoAdmin);
            }
            return OceanReturn.successResult(
                    "获取所有分类成功",
                    dataList
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
     * 添加/编辑分类接口
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/addAndEditClassify",method = RequestMethod.POST)
    public Result addAndEditClassify(@RequestBody JSONObject jsonObject) {
        String classifyName = null;
        Integer parentId = null;
        Integer selfDbId = null;    // 当新增分类时，该值为-1
        try {
            classifyName = jsonObject.getString("classifyName");
            parentId = jsonObject.getInteger("parentId");
            selfDbId = jsonObject.getInteger("selfDbId");
            if (selfDbId == -1){
                // 新增分类
                couseClassifyService.addNewClassify(classifyName,parentId);
            }else {
                // 修改分类信息
                couseClassifyService.editClassifyInfo(selfDbId,classifyName,parentId);
            }
            return OceanReturn.successResult(
                    "操作成功",
                    courseBusiness.getTreeClassify()
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }
    }

    /**
     * 删除分类
     * 分类删除成功后，该分类下的所有课程也将全部删除
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/deleteClassify",method = RequestMethod.POST)
    public Result deleteClassify(@RequestBody JSONObject jsonObject){
        Integer delClassifyId = null;
        try {
            delClassifyId = jsonObject.getInteger("delId");
            couseClassifyService.deleteClassifyAndAllChildClassify(delClassifyId);
            return OceanReturn.successResult(
                    "删除成功",
                    courseBusiness.getTreeClassify()
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }
    }


    /**
     * 课程搜索
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/searchCourses",method = RequestMethod.POST)
    public Result searchCourses(@RequestBody JSONObject jsonObject){
        long firstTime = OceanDateUtil.getCurrentTime();
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
                int count = userCourseStudyService.getCourseStudyCount(courseVo.getDbId());
                courseVo.setStudyCount(count);
                // 设置是否已经推荐至首页轮播
                courseVo.setHomeBannerS(homeBannerService.isCourseBelongToBanner(courseVo.getDbId()));
                resultCourseVoList.add(courseVo);
            }
            Map<String,Object> data = new HashMap<>();
            data.put("courseList",resultCourseVoList);
            data.put("count",coursePoList.size());
            long endTime = OceanDateUtil.getCurrentTime();
            System.out.println("本次搜索接口耗时：< " + (endTime - firstTime) + " >毫秒");
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

    /**
     * 操作课程是否推荐至轮播
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/operateCourseToBanner",method = RequestMethod.POST)
    public Result operateCourseToBanner(@RequestBody JSONObject jsonObject){
        int courseId = 0;
        boolean isRecommend = false;
        try {
            courseId = jsonObject.getInteger("courseId");
            /**
             * true 为推荐轮播
             * false 为下架轮播
             */
            isRecommend = jsonObject.getBoolean("isRecommend");
            if (isRecommend){
                homeBannerService.addCourseToBanner(courseId);
            }else {
                homeBannerService.deleteCourseFromBanner(courseId);
            }
            return OceanReturn.successResult(
                    "操作成功",
                    null
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    !isRecommend
            );
        }
    }

    @RequestMapping(value = "/operateCourseIsPutAway",method = RequestMethod.POST)
    public Result operateCourseIsPutAway(@RequestBody JSONObject jsonObject){
        int courseId = 0;
        boolean isPutAway = false;
        try {
            courseId = jsonObject.getInteger("courseId");
            isPutAway = jsonObject.getBoolean("isPutAway");
            /**
             * true 为上架
             * false 为下架
             */
            courseService.editCourseIsPutAway(courseId,isPutAway);
            return OceanReturn.successResult(
                    "操作成功",
                    null
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    !isPutAway
            );
        }
    }

    /**
     * 删除一条课程记录
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/deleteCourseItem",method = RequestMethod.POST)
    public Result deleteCourseItem(@RequestBody JSONObject jsonObject){
        int courseId = 0;
        try {
            courseId = jsonObject.getInteger("courseId");
            courseService.deleteCourseItem(courseId);
            return OceanReturn.successResult(
                    "删除成功",
                    null
            );        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }
    }

    /**
     * 获取课程章节
     * @return
     */
    @RequestMapping(value = "/getCourseChapterAndSection",method = RequestMethod.POST)
    public Result getCourseChapterAndSection(@RequestBody JSONObject jsonObject){
        Integer courseId = null;
        try {
            courseId = jsonObject.getInteger("courseId");
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

    /**
     * 新增章节
     * @return
     */
    @RequestMapping(value = "/addChapterAndSection",method = RequestMethod.POST)
    public Result addChapterAndSection(@RequestBody JSONObject jsonObject){
        try {
            CourseSectionVo courseSectionVo  = CourseSectionVo.builder()
                    .courseId(jsonObject.getInteger("courseId"))
                    .parentId(jsonObject.getInteger("parentId"))
                    .sectionName(jsonObject.getString("sectionName"))
                    .videoUrl(jsonObject.getString("videoUrl"))
                    .build();
            courseBusiness.addNewChapterAndSection(courseSectionVo);
            return OceanReturn.successResult(
                    "新增章节成功",
                    null
            );
        } catch (NullPointerException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    "新增章节失败，参数有误",
                    null
            );
        } catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }catch (Exception e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    "新增章节失败,未知异常",
                    null
            );
        }
    }

    @RequestMapping(value = "/editChapterAndSection",method = RequestMethod.POST)
    public Result editChapterAndSection(@RequestBody JSONObject jsonObject){
       try {
           CourseSectionVo courseSectionVo  = CourseSectionVo.builder()
                   .dbId(jsonObject.getInteger("dbId"))
                   .courseId(jsonObject.getInteger("courseId"))
                   .parentId(jsonObject.getInteger("parentId"))
                   .sectionName(jsonObject.getString("sectionName"))
                   .videoUrl(jsonObject.getString("videoUrl"))
                   .build();
           courseBusiness.editChapterAndSection(courseSectionVo);
           return OceanReturn.successResult(
                   "编辑课程成功",
                   null
           );
       } catch (NullPointerException e){
           e.printStackTrace();
           return OceanReturn.errorResult(
                   "编辑章节失败，参数有误",
                   null
           );
       } catch (RuntimeException e){
           e.printStackTrace();
           return OceanReturn.errorResult(
                   e.getMessage(),
                   null
           );
       }catch (Exception e){
           e.printStackTrace();
           return OceanReturn.errorResult(
                   "编辑章节失败,未知异常",
                   null
           );
       }
    }

    /**
     * 删除章节
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/deleteChapterAndSection",method = RequestMethod.POST)
    public Result deleteChapterAndSection(@RequestBody JSONObject jsonObject) {
        Integer sectionDbId = null;
        try {
            sectionDbId = jsonObject.getInteger("dbId");
            courseSectionService.deleteSection(sectionDbId);
            return OceanReturn.successResult(
                    "删除成功",
                    null
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }
    }


    @RequestMapping(value = "/getAllComment",method = RequestMethod.POST)
    public Result getAllComment(@RequestBody JSONObject jsonObject) {
        Integer page = null;
        Integer size = null;
        Integer sortType = null;
        String sortProp = null;
        try {
            page = jsonObject.getInteger("page");
            size = jsonObject.getInteger("size");
            sortType = jsonObject.getInteger("sortType");
            sortProp = jsonObject.getString("sortProp");
            Sort sort = null;
            if (sortType == 0){
                sort = new Sort(Sort.Direction.ASC,sortProp);
            }else {
                sort = new Sort(Sort.Direction.DESC,sortProp);
            }
            Pageable pageable = new PageRequest(page,size,sort);
            return OceanReturn.successResult(
                    "获取评论列表成功",
                    courseBusiness.getCommentsManagerData(pageable)
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }
    }


    @RequestMapping(value = "/deleteComment",method = RequestMethod.POST)
    public Result deleteComment(@RequestBody JSONObject jsonObject) {
        Integer delId = null;
        try {
            delId = jsonObject.getInteger("delId");
            courseCommentService.deleteOneComment(delId);
            return OceanReturn.successResult(
                    "删除成功",
                    null
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
