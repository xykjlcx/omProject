package com.xykj.omservice.user.po;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user_course_study", schema = "omdb", catalog = "")
public class TUserCourseStudyPo {
    /**
     * 自增id
     */
    @Id@Column(name = "id", nullable = false)
    private int id;

    /**
     * 用户id
     */
    @Basic@Column(name = "user_id", nullable = false)
    private int userId;

    /**
     * 课程id
     */
    @Basic@Column(name = "course_id", nullable = false)
    private int courseId;

    /**
     * 课程章节id
     */
    @Basic@Column(name = "section_id", nullable = true)
    private Integer sectionId;

    /**
     * 最早开始学习时间
     */
    @Basic@Column(name = "first_study_time", nullable = true)
    private Timestamp firstStudyTime;

    /**
     * 最近学习时间
     */
    @Basic@Column(name = "last_study_time", nullable = true)
    private Timestamp lastStudyTime;

    /**
     * 创建时间
     */
    @Basic@Column(name = "create_time", nullable = false)
    private Timestamp createTime;

    /**
     * 状态
     */
    @Basic@Column(name = "status", nullable = true)
    private Integer status;

}
