package com.xykj.omapp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ocean
 * @Title: MyCommentVo
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/6上午11:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyCommentVo {

    private Integer id;
    private Integer userId;
    private String commentContent;
    private String commentTime;
    private CourseVo courseVo;

}
