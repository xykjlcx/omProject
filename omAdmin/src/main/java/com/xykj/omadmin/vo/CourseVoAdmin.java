package com.xykj.omadmin.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ocean
 * @Title: CourseVoAdmin
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/13下午6:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseVoAdmin {

    private Integer id;
    private Integer dbId;
    private String courseName;
    private String courseDesc;
    private Integer classifyId;
    private String duration;
    private String level;
    private String imgUrl;
    private String videoUrl;
    private Integer isFree;
    private Double price;
    private Integer count;

}
