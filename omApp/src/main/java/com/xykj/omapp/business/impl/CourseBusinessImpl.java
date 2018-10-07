package com.xykj.omapp.business.impl;

import com.xykj.omapp.business.ICourseBusiness;
import com.xykj.omapp.utils.PoConvertVo;
import com.xykj.omapp.vo.CourseCommentVo;
import com.xykj.omapp.vo.CourseSectionVo;
import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.ombase.utils.error.OceanException;
import com.xykj.omservice.course.po.TCourseCommentPo;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.po.TCourseSectionPo;
import com.xykj.omservice.course.services.impl.CourseCommentServiceImpl;
import com.xykj.omservice.course.services.impl.CourseSectionServiceImpl;
import com.xykj.omservice.course.services.impl.CourseServiceImpl;
import com.xykj.omservice.user.dao.UserDao;
import com.xykj.omservice.user.po.TUserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseBusinessImpl implements ICourseBusiness {

    @Autowired
    CourseServiceImpl courseService;
    @Autowired
    CourseSectionServiceImpl courseSectionService;
    @Autowired
    CourseCommentServiceImpl courseCommentService;
    @Autowired
    UserDao userDao;

    @Override
    public Map<String, Object> getChapterAndSection(int courseId) {
        Map<String,Object> data = null;
        List<CourseSectionVo> chapterVoList = new ArrayList<>();
        List<List<CourseSectionVo>> sectionVoList = new ArrayList<>();
        try {
            data = courseSectionService.getAllChapterAndSection(courseId);
            List<TCourseSectionPo> chapteList = (List<TCourseSectionPo>) data.get("chapter");
            List<List<TCourseSectionPo>> sectionList = (List<List<TCourseSectionPo>>) data.get("section");
            chapteList.forEach(tCourseSectionPo -> chapterVoList.add(PoConvertVo.convert(tCourseSectionPo)));
            sectionList.forEach(tCourseSectionPos -> {
                List<CourseSectionVo> temp = new ArrayList<>();
                tCourseSectionPos.forEach(tCourseSectionPo -> {
                    temp.add(PoConvertVo.convert(tCourseSectionPo));
                });
                sectionVoList.add(temp);
            });
            data.put("chapter",chapterVoList);
            data.put("section",sectionVoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<CourseCommentVo> getCourseCommentsByCourseIdForPage(int courseId, Pageable pageable) throws RuntimeException {
        List<CourseCommentVo> courseCommentVoList = new ArrayList<>();
        try {
            List<TCourseCommentPo> tCourseCommentPoList = courseCommentService.getAllCommentByCourseIdForPage(courseId,pageable);
            tCourseCommentPoList.forEach(tCourseCommentPo -> {
                 System.out.println("随便我:" + tCourseCommentPo.getUserId());       // 测试是否获取用户id
                List<TUserPo> getUserList = userDao.findAllById(tCourseCommentPo.getUserId());
                if (OceanOperationUtil.isNotNullOrEmptyForCollection(getUserList)){
                        courseCommentVoList.add(PoConvertVo.convert(tCourseCommentPo,getUserList.get(0)));
                }else {
                    // 评论的主人用户已经不存在le
                }
            });
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
        return courseCommentVoList;
    }


}
