package com.xykj.omapp.utils;

import com.xykj.omapp.vo.*;
import com.xykj.ombase.utils.OceanDateUtil;
import com.xykj.omservice.course.po.TCourseCommentPo;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.po.TCourseSectionPo;
import com.xykj.omservice.user.po.TUserPo;

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

    public static CourseCommentVo convert(TCourseCommentPo tCourseCommentPo, TUserPo tUserPo){
        return CourseCommentVo.builder()
                .id(tCourseCommentPo.getId())
                .userId(tCourseCommentPo.getUserId())
                .userName(tUserPo.getUserName())
                .userHeadImgUrl(tUserPo.getHeadImg())
                .courseId(tCourseCommentPo.getCourseId())
                .commentContent(tCourseCommentPo.getCommentContent())
                .commentTime(OceanDateUtil.converDate(tCourseCommentPo.getCreateTime().getTime()))
                .build();
    }

    public static UserVo convert(TUserPo tUserPo){
        return UserVo.builder()
                .id(tUserPo.getId())
                .account(tUserPo.getUserName())
                .realName(tUserPo.getRealName())
                .signature(tUserPo.getSignature())
                .email(tUserPo.getEmail())
                .gender(tUserPo.getGender())
                .education(tUserPo.getEducation())
                .phone(tUserPo.getPhone())
                .headImg(tUserPo.getHeadImg())
                .lastLoginTime(OceanDateUtil.converDate(tUserPo.getLastLoginTime().getTime()))
                .build();
    }

    public static MyCommentVo convert(TCourseCommentPo courseCommentPo,CourseVo courseVo){
        return MyCommentVo.builder()
                .id(courseCommentPo.getId())
                .userId(courseCommentPo.getUserId())
                .commentContent(courseCommentPo.getCommentContent())
                .commentTime(OceanDateUtil.converDate(courseCommentPo.getCreateTime().getTime()))
                .courseVo(courseVo)
                .build();

    }

}
