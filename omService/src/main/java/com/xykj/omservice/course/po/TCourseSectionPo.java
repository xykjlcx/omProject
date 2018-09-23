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
@Table(name = "t_course_section", schema = "omdb", catalog = "")
public class TCourseSectionPo {
    /**
     * 自增id
     */
    @Id@Column(name = "id", nullable = false)@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 章节名称
     */
    @Basic@Column(name = "section_name", nullable = false, length = 255)
    private String sectionName;

    /**
     * 父级章节id
     */
    @Basic@Column(name = "parent_id", nullable = false)
    private int parentId;

    /**
     * 同级别章节排序
     */
    @Basic@Column(name = "sequence", nullable = true)
    private Integer sequence;

    /**
     * 所属课程
     */
    @Basic@Column(name = "course_id", nullable = false)
    private int courseId;

    /**
     * 章节时长
     */
    @Basic@Column(name = "duration", nullable = true, length = 255)
    private String duration;

    /**
     * 视频地址
     */
    @Basic@Column(name = "video_url", nullable = true, length = 255)
    private String videoUrl;

    /**
     * 创建时间
     */
    @Basic@Column(name = "create_section_time", nullable = true)
    private Timestamp createSectionTime;

    /**
     * 更新时间
     */
    @Basic@Column(name = "update_section_time", nullable = true)
    private Timestamp updateSectionTime;

}
