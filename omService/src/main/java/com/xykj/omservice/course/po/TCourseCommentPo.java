package com.xykj.omservice.course.po;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author ocean
 * @Title: TCourseCommentPo
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/2下午4:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_course_comment", schema = "omdb", catalog = "")
public class TCourseCommentPo {
    @Id@Column(name = "id")@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic@Column(name = "user_id")
    private Integer userId;
    @Basic@Column(name = "course_id")
    private Integer courseId;
    @Basic@Column(name = "section_id")
    private Integer sectionId;
    @Basic@Column(name = "comment_content")
    private String commentContent;
    @Basic@Column(name = "create_time")
    private Timestamp createTime;

}
