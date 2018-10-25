package com.xykj.omservice.course.services.impl;

import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.course.dao.CourseSectionDao;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.po.TCourseSectionPo;
import com.xykj.omservice.course.services.ICourseSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ocean
 * @Title: CourseSectionServiceImpl
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/2下午11:11
 */
@Service
public class CourseSectionServiceImpl implements ICourseSectionService {

    @Autowired
    CourseSectionDao courseSectionDao;
    @Autowired
    CourseDao courseDao;

    @Override
    public Map<String,Object> getAllChapterAndSection(int courseId) throws RuntimeException {
        Map<String,Object> data = new HashMap<>();
        List<TCourseSectionPo> chapterPoList = null;                              // 章PO
        List<List<TCourseSectionPo>> sectionPoList = new ArrayList<>();            // 节PO
        List<TCoursePo> coursePoList = courseDao.findAllById(courseId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(coursePoList)){
            throw new NullPointerException("Id为：" + courseId + ",的课程不存在");
        }
        chapterPoList = courseSectionDao.findAllByCourseIdAndParentIdOrderBySequenceAsc(courseId,0);
        if (OceanOperationUtil.isNullOrEmptyForCollection(chapterPoList)){
            throw new NullPointerException("该课程下没有任何章节");
        }
        chapterPoList.forEach(tCourseSectionPo -> {
            List<TCourseSectionPo> temp = courseSectionDao.findAllByCourseIdAndParentIdOrderBySequenceAsc(courseId,tCourseSectionPo.getId());
            sectionPoList.add(temp);
        });
        data.put("chapter",chapterPoList);
        data.put("section",sectionPoList);
        return data;
    }

    @Override
    public void addNewSection(TCourseSectionPo tCourseSectionPo) throws RuntimeException {
        if (tCourseSectionPo == null){
            throw new RuntimeException("新增章节数据不完整");
        }
        Integer beforeMaxSqu = courseSectionDao.getMaxsequenceByParentId(tCourseSectionPo.getParentId());
        if(beforeMaxSqu == null){
            tCourseSectionPo.setSequence(0);
        }else {
            tCourseSectionPo.setSequence(beforeMaxSqu + 1);
        }
        tCourseSectionPo.setCreateSectionTime(new Timestamp(System.currentTimeMillis()));
        tCourseSectionPo.setUpdateSectionTime(new Timestamp(System.currentTimeMillis()));
        tCourseSectionPo.setDuration("66");
        courseSectionDao.save(tCourseSectionPo);
    }

    @Override
    public void editSection(TCourseSectionPo editCourseSectionPo) throws RuntimeException {
        if (editCourseSectionPo == null){
            throw new RuntimeException("章节数据不完整");
        }
        TCourseSectionPo checkOldCourseSectionPo = courseSectionDao.findById(editCourseSectionPo.getId()).get();
        if (checkOldCourseSectionPo == null){
            throw new RuntimeException("编辑的章节不存在");
        }
        if (editCourseSectionPo.getParentId() == checkOldCourseSectionPo.getParentId()){
            // 章/节 type  没有发生改变

        }else {
            checkOldCourseSectionPo.setParentId(editCourseSectionPo.getParentId());
            Integer beforeMaxSqu = courseSectionDao.getMaxsequenceByParentId(editCourseSectionPo.getParentId());
            if (beforeMaxSqu == null){
                checkOldCourseSectionPo.setSequence(0);
            }else {
                checkOldCourseSectionPo.setSequence(beforeMaxSqu + 1);
            }
        }
        checkOldCourseSectionPo.setSectionName(editCourseSectionPo.getSectionName());
        checkOldCourseSectionPo.setVideoUrl(editCourseSectionPo.getVideoUrl());
        checkOldCourseSectionPo.setUpdateSectionTime(new Timestamp(System.currentTimeMillis()));
        courseSectionDao.saveAndFlush(checkOldCourseSectionPo);
    }

    @Override
    public void deleteSection(int courseSectionId) throws RuntimeException {
        List<TCourseSectionPo> checkSectionPoList = courseSectionDao.findAllById(courseSectionId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkSectionPoList)){
            throw new RuntimeException("删除失败，章节不存在");
        }
        courseSectionDao.delete(checkSectionPoList.get(0));
    }
}
