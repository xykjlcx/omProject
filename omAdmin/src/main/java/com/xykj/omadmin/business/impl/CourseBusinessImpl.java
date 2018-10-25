package com.xykj.omadmin.business.impl;

import com.xykj.omadmin.business.ICourseBusiness;
import com.xykj.omadmin.utils.PoConvertVo;
import com.xykj.omadmin.utils.VoConvertPo;
import com.xykj.omadmin.vo.CourseSectionVo;
import com.xykj.omservice.course.po.TCourseSectionPo;
import com.xykj.omservice.course.services.ICourseCommentService;
import com.xykj.omservice.course.services.impl.CourseSectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ocean
 * @Title: CourseBusinessImpl
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/21下午3:47
 */
@Service
public class CourseBusinessImpl implements ICourseBusiness {
    @Autowired
    CourseSectionServiceImpl courseSectionService;

    @Override
    public Map<String, Object> getChapterAndSection(int courseId) throws RuntimeException {
        Map<String,Object> data = null;
        List<CourseSectionVo> chapterVoList = new ArrayList<>();
        List<List<CourseSectionVo>> sectionVoList = new ArrayList<>();
        try {
            data = courseSectionService.getAllChapterAndSection(courseId);
            List<TCourseSectionPo> chapteList = (List<TCourseSectionPo>) data.get("chapter");
            List<List<TCourseSectionPo>> sectionList = (List<List<TCourseSectionPo>>) data.get("section");
            for (int i = 0; i < chapteList.size(); i++) {
                TCourseSectionPo tCourseSectionPo = chapteList.get(i);
                CourseSectionVo courseSectionVo = PoConvertVo.convert(tCourseSectionPo);
                courseSectionVo.setId(i+1);
                chapterVoList.add(courseSectionVo);
            }
//            sectionList.forEach(tCourseSectionPos -> {
//                List<CourseSectionVo> temp = new ArrayList<>();
//                tCourseSectionPos.forEach(tCourseSectionPo -> {
//                    temp.add(PoConvertVo.convert(tCourseSectionPo));
//                });
//                sectionVoList.add(temp);
//            });
            for (int i = 0; i < sectionList.size(); i++) {
                List<TCourseSectionPo> tempPoList = sectionList.get(i);
                List<CourseSectionVo> tempVoList = new ArrayList<>();
                for (int i1 = 0; i1 < tempPoList.size(); i1++) {
                    TCourseSectionPo tempPo = tempPoList.get(i1);
                    CourseSectionVo temVo = PoConvertVo.convert(tempPo);
                    temVo.setId(i1+1);
                    tempVoList.add(temVo);
                }
                sectionVoList.add(tempVoList);
            }
            data.put("chapter",chapterVoList);
            data.put("section",sectionVoList);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
        return data;
    }

    @Override
    public void addNewChapterAndSection(CourseSectionVo sectionVo) throws RuntimeException {
       try {
           if (sectionVo == null){
               throw new RuntimeException("新增章节不能为空");
           }
           TCourseSectionPo tCourseSectionPo = VoConvertPo.convert(sectionVo);
           courseSectionService.addNewSection(tCourseSectionPo);
       }catch (RuntimeException e){
           e.printStackTrace();
           throw e;
       }
    }

    @Override
    public void editChapterAndSection(CourseSectionVo courseSectionVo) throws RuntimeException {
        try {
            if (courseSectionVo == null){
                throw new RuntimeException("编辑章节信息不完整");
            }
            TCourseSectionPo tCourseSectionPo = VoConvertPo.convert(courseSectionVo);
            tCourseSectionPo.setId(courseSectionVo.getDbId());
            courseSectionService.editSection(tCourseSectionPo);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }



}
