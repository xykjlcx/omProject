package com.xykj.omadmin.utils;

import com.xykj.omadmin.vo.CourseClassifyVoAdmin;
import com.xykj.omadmin.vo.CourseSectionVo;
import com.xykj.omadmin.vo.CourseVoAdmin;
import com.xykj.ombase.utils.OceanDateUtil;
import com.xykj.omservice.course.po.TCourseClassifyPo;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.po.TCourseSectionPo;

/**
 * @author ocean
 * @Title: PoConvertVo
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/13下午6:43
 */
public class PoConvertVo {

    public static CourseVoAdmin convert(TCoursePo tCoursePo,int id){
        boolean isPutawayBoolean = false;
        if (tCoursePo.getIsPutaway() == 0){
            isPutawayBoolean = true;
        }else {
            isPutawayBoolean = false;
        }
        return CourseVoAdmin.builder()
                .id(id)
                .dbId(tCoursePo.getId())
                .courseName(tCoursePo.getCourseName())
                .courseDesc(tCoursePo.getCourseDesc())
                .classifyId(tCoursePo.getClassifyId())
                .duration(tCoursePo.getDuration())
                .level(tCoursePo.getLevel())
                .previewImg(tCoursePo.getPreviewImg())
                .videoUrl(tCoursePo.getVideoUrl())
                .isFree(tCoursePo.getIsFree())
                .isPutaway(tCoursePo.getIsPutaway())
                .isPutawayS(isPutawayBoolean)
                .price(tCoursePo.getPrice())
                .weight(tCoursePo.getWeight())
                .createCourseTime(OceanDateUtil.converDate(tCoursePo.getCreateCourseTime().getTime()))
                .updateCourseTime(OceanDateUtil.converDate(tCoursePo.getUpdateCourseTime().getTime()))
                .build();
        // studyCount属性，controller中赋值
    }

    public static CourseClassifyVoAdmin convert(TCourseClassifyPo tCourseClassifyPo,int id){
        return CourseClassifyVoAdmin.builder()
                .id(id)
                .dbId(tCourseClassifyPo.getId())
                .classifyName(tCourseClassifyPo.getClassifyName())
                .parentId(tCourseClassifyPo.getParentId())
                .sequence(tCourseClassifyPo.getSequence())
                .build();
    }

    public static CourseSectionVo convert(TCourseSectionPo tCourseSectionPo){
        return CourseSectionVo.builder()
                .dbId(tCourseSectionPo.getId())
                .courseId(tCourseSectionPo.getCourseId())
                .parentId(tCourseSectionPo.getParentId())
                .sectionName(tCourseSectionPo.getSectionName())
                .duration(tCourseSectionPo.getDuration())
                .videoUrl(tCourseSectionPo.getVideoUrl())
                .build();
    }

}
