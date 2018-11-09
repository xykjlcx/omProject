package com.xykj.omadmin.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ocean
 * @Title: CourseCommentVoAdmin
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/11/89:59 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseCommentVoAdmin {

    /**
     * id
     */
    private Integer id;
    /**
     * 数据库实际id
     */
    private Integer dbId;
    /**
     * 用户头像
     */
    private String userHeadImg;
    /**
     * 用户账户
     */
    private String userAccount;
    /**
     * 用户真实姓名
     */
    private String realName;
    /**
     * 评论课程名称
     */
    private String courseName;
    /**
     * 评论内容
     */
    private String commentContent;
    /**
     * 评论所属章节名称
     */
    private String commentBelongSection;
    /**
     * 评论时间
     */
    private String commentTime;

}
