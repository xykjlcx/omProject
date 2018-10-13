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
@Table(name = "t_course", schema = "omdb", catalog = "")
public class TCoursePo {
    /**
     * 自增id
     */
    @Id@Column(name = "id", nullable = false)@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 课程名称
     */
    @Basic@Column(name = "course_name", nullable = false, length = 255)
    private String courseName;

    /**
     * 课程描述
     */
    @Basic@Column(name = "course_desc", nullable = false, length = 255)
    private String courseDesc;

    /**
     * 所属分类
     */
    @Basic@Column(name = "classify_id", nullable = false)
    private int classifyId;

    /**
     * 课程时长
     */
    @Basic@Column(name = "duration", nullable = true, length = 255)
    private String duration;

    /**
     * 课程地位级别
     */
    @Basic@Column(name = "level", nullable = true, length = 255)
    private String level;

    /**
     * 课程预览图
     */
    @Basic@Column(name = "preview_img", nullable = false, length = 255)
    private String previewImg;

    /**
     * 预览视频地址
     */
    @Basic@Column(name = "video_url", nullable = true, length = 255)
    private String videoUrl;

    /**
     * 创建时间
     */
    @Basic@Column(name = "create_course_time", nullable = true)
    private Timestamp createCourseTime;

    /**
     * 更新时间
     */
    @Basic@Column(name = "update_course_time", nullable = true)
    private Timestamp updateCourseTime;

    /**
     * 是否上架
     */
    @Basic@Column(name = "is_putaway", nullable = true)
    private Integer isPutaway;

    /**
     * 权重
     */
    @Basic@Column(name = "weight", nullable = true)
    private Integer weight;

    /**
     * 是否免费
     */
    @Basic@Column(name = "is_free", nullable = true)
    private Integer isFree;

    /**
     * 价格
     */
    @Basic@Column(name = "price", nullable = true)
    private Double price;

    /**
     * 收藏数
     */
    @Basic@Column(name = "collect_count", nullable = true)
    private Integer collectCount;

}
