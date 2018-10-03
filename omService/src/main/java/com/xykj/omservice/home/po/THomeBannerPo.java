package com.xykj.omservice.home.po;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_home_banner", schema = "omdb", catalog = "")
public class THomeBannerPo {
    /**
     * 自增id
     */
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "course_id",nullable = false)
    private int courseId;

    /**
     * 权重
     */
    @Basic@Column(name = "weight", nullable = false, precision = 0)
    private double weight;

    /**
     * 创建时间
     */
    @Basic@Column(name = "create_banner_time", nullable = false)
    private Timestamp createBannerTime;

    /**
     * 更新时间
     */
    @Basic@Column(name = "update_banner_time", nullable = false)
    private Timestamp updateBannerTime;

}
