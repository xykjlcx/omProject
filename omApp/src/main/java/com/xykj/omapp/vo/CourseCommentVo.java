package com.xykj.omapp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ocean
 * @Title: CourseCommentVo
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/3上午10:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseCommentVo {

    private Integer id;
    private Integer userId;
    private String userName;
    private String userHeadImgUrl;
    private Integer courseId;
    private String commentContent;
    private String commentTime;

}
