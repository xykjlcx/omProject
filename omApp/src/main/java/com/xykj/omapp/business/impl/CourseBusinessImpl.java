package com.xykj.omapp.business.impl;

import com.xykj.omapp.business.ICourseBusiness;
import com.xykj.omapp.utils.PoConvertVo;
import com.xykj.omapp.vo.CourseSectionVo;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.po.TCourseSectionPo;
import com.xykj.omservice.course.services.impl.CourseSectionServiceImpl;
import com.xykj.omservice.course.services.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<TCoursePo> getRecommendCourse() {
        try {
//            List<TCoursePo> coursePoList = courseService.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TCoursePo> getGuessLikeCourse() {
        return null;
    }

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


}
