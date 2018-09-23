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
@Table(name = "t_course_classify", schema = "omdb", catalog = "")
public class TCourseClassifyPo {
    /**
     * 自增id
     */
    @Id@Column(name = "id", nullable = false)@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 分类名
     */
    @Basic@Column(name = "classify_name", nullable = false, length = 255)
    private String classifyName;

    /**
     * 父级分类id
     */
    @Basic@Column(name = "parent_id", nullable = true)
    private Integer parentId;

    /**
     * 同级别分类排序
     */
    @Basic@Column(name = "sequence", nullable = true, length = 255)
    private String sequence;

    /**
     * 创建时间
     */
    @Basic@Column(name = "create_classify_time", nullable = true)
    private Timestamp createClassifyTime;

    /**
     * 更新时间
     */
    @Basic@Column(name = "update_classify_time", nullable = true)
    private Timestamp updateClassifyTime;

}
