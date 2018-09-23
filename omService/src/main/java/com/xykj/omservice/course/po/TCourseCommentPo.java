package com.xykj.omservice.course.po;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_course_comment", schema = "omdb", catalog = "")
public class TCourseCommentPo {
    /**
     * 自增id
     */
    @Id@Column(name = "id", nullable = false)@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 评论内容
     */
    @Basic@Column(name = "comment_content", nullable = false, length = 255)
    private String commentContent;

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
     * 章节id
     *
     */
    @Basic@Column(name = "section_id", nullable = false)
    private int sectionId;

    /**
     * 评论时间
     */
    @Basic@Column(name = "create_comment_time", nullable = false)
    private Timestamp createCommentTime;

}
