package com.xykj.omapp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseVo {

    private Integer id;
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
