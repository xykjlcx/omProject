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

    /**
     * 轮播文字
     */
    @Basic@Column(name = "banner_text", nullable = false, length = 255)
    private String bannerText;

    /**
     * 轮播预览图
     */
    @Basic@Column(name = "banner_preview_img", nullable = false, length = 255)
    private String bannerPreviewImg;

    /**
     * 点击url地址
     */
    @Basic@Column(name = "banner_url_address", nullable = false, length = 255)
    private String bannerUrlAddress;

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
