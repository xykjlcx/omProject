package com.xykj.omadmin.utils;

import com.alibaba.fastjson.JSONObject;
import com.xykj.omservice.course.po.TCoursePo;

/**
 * @author ocean
 * @Title: VoConvertPo
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/13下午7:21
 */
public class VoConvertPo {

    public static class CoursePoKey{
        public static final String ID = "id";
        public static final String COURSE_NAME = "courseName";
        public static final String COURSE_DESC = "courseDesc";
        public static final String CLASSIFY_ID = "classifyId";
        public static final String DURATION = "duration";
        public static final String LEVEL = "level";
        public static final String IMG_URL = "previewImg";
        public static final String VIDEO_URL = "videoUrl";
        public static final String IS_PUTWAY = "isPutaway";
        public static final String WEIGHT = "weight";
        public static final String IS_FREE = "isFree";
        public static final String PRICE = "price";
    }

    public static TCoursePo convert(JSONObject jsonObject){
        TCoursePo tCoursePo = null;
        try {
            Integer id = id = jsonObject.getInteger("id");
            tCoursePo = TCoursePo.builder()
                    .courseName(jsonObject.getString(CoursePoKey.COURSE_NAME))
                    .courseDesc(jsonObject.getString(CoursePoKey.COURSE_DESC))
                    .classifyId(jsonObject.getInteger(CoursePoKey.CLASSIFY_ID))
                    .duration(jsonObject.getString(CoursePoKey.DURATION))
                    .level(jsonObject.getString(CoursePoKey.LEVEL))
                    .previewImg(jsonObject.getString(CoursePoKey.IMG_URL))
                    .videoUrl(jsonObject.getString(CoursePoKey.VIDEO_URL))
                    .isPutaway(jsonObject.getInteger(CoursePoKey.IS_PUTWAY))
                    .weight(jsonObject.getInteger(CoursePoKey.WEIGHT))
                    .isFree(jsonObject.getInteger(CoursePoKey.IS_FREE))
                    .price(jsonObject.getDouble(CoursePoKey.PRICE))
                    .build();
            if (id != null){
                tCoursePo.setId(id);
            }
        }catch (Exception e){
            throw new RuntimeException("数据有误");
        }
        return tCoursePo;
    }

}
