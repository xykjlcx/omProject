package com.xykj.omservice.course.services.impl;

import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.course.dao.CourseSectionDao;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.po.TCourseSectionPo;
import com.xykj.omservice.course.services.ICourseSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
