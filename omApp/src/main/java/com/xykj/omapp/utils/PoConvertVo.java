package com.xykj.omapp.utils;

import com.xykj.omapp.vo.CourseSectionVo;
import com.xykj.omapp.vo.CourseVo;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.po.TCourseSectionPo;

public class PoConvertVo {

    public static CourseVo convert(TCoursePo tCoursePo){
        return CourseVo.builder()
                .id(tCoursePo.getId())
                .courseName(tCoursePo.getCourseName())
                .courseDesc(tCoursePo.getCourseDesc())
                .classifyId(tCoursePo.getClassifyId())
                .duration(tCoursePo.getDuration())
                .level(tCoursePo.getLevel())
                .imgUrl(tCoursePo.getPreviewImg())
                .videoUrl(tCoursePo.getVideoUrl())
                .isFree(tCoursePo.getIsFree())
                .price(tCoursePo.getPrice())
                .count(tCoursePo.getCollectCount())
                .build();
    }

    public static CourseSectionVo convert(TCourseSectionPo tCourseSectionPo){
        return CourseSectionVo.builder()
                .id(tCourseSectionPo.getId())
                .courseId(tCourseSectionPo.getCourseId())
                .parentId(tCourseSectionPo.getParentId())
                .sectionName(tCourseSectionPo.getSectionName())
                .duration(tCourseSectionPo.getDuration())
                .videoUrl(tCourseSectionPo.getVideoUrl())
                .build();
    }

}
