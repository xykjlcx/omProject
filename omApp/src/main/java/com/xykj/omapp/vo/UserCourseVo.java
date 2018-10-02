package com.xykj.omapp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author ocean
 * @Title: UserCourseVo
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/1下午5:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCourseVo {

    private Integer id;
    private CourseVo courseVo;
    private Integer sectionId;
    private String sectionName;
    private String firstStudyTime;
    private String lastStudyTime;


}
