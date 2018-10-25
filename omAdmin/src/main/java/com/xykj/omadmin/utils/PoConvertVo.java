package com.xykj.omadmin.utils;

import com.xykj.omadmin.vo.CourseClassifyVoAdmin;
import com.xykj.omadmin.vo.CourseSectionVo;
import com.xykj.omadmin.vo.CourseVoAdmin;
import com.xykj.omadmin.vo.UserVoAdmin;
import com.xykj.ombase.utils.OceanDateUtil;
import com.xykj.omservice.course.po.TCourseClassifyPo;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.po.TCourseSectionPo;
import com.xykj.omservice.user.po.TUserPo;

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

    public static UserVoAdmin convert(TUserPo tUserPo,int id) {
        UserVoAdmin userVoAdmin =  UserVoAdmin.builder()
                .id(id)
                .dbId(tUserPo.getId())
                .userName(tUserPo.getUserName())
                .email(tUserPo.getEmail())
                .realName(tUserPo.getRealName())
                .avator(tUserPo.getHeadImg())
                .status(tUserPo.getStatus())
                .build();
        String genderStr = "";
        switch (tUserPo.getGender()){
            case 0:
                genderStr = "男";
                break;
            case 1:
                genderStr = "女";
                break;
            default:
                genderStr = "人妖?";
                break;
        }
        userVoAdmin.setGenderStr(genderStr);
        String edustr = "";
        switch (tUserPo.getEducation()){
            case 0:
                edustr = "专科";
                break;
            case 1:
                edustr = "本科";
                break;
            case 2:
                edustr = "研究生";
                break;
            case 3:
                edustr = "博士";
                break;
            case 4:
                edustr = "其他";
                break;
            default:
                edustr = "秘密杀手?";
                break;
        }
        if (tUserPo.getStatus() == 0){
            userVoAdmin.setStatusBool(false);
        }else {
            userVoAdmin.setStatusBool(true);
        }
        userVoAdmin.setEducation(edustr);
        return userVoAdmin;
    }


}
