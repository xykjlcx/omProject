package com.xykj.omservice.home.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_home_banner", schema = "omdb", catalog = "")
public class THomeBannerPo {
    private int id;
    private String bannerText;
    private String bannerPreviewImg;
    private String bannerUrlAddress;
    private double weight;
    private Timestamp createBannerTime;
    private Timestamp updateBannerTime;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "banner_text", nullable = false, length = 255)
    public String getBannerText() {
        return bannerText;
    }

    public void setBannerText(String bannerText) {
        this.bannerText = bannerText;
    }

    @Basic
    @Column(name = "banner_preview_img", nullable = false, length = 255)
    public String getBannerPreviewImg() {
        return bannerPreviewImg;
    }

    public void setBannerPreviewImg(String bannerPreviewImg) {
        this.bannerPreviewImg = bannerPreviewImg;
    }

    @Basic
    @Column(name = "banner_url_address", nullable = false, length = 255)
    public String getBannerUrlAddress() {
        return bannerUrlAddress;
    }

    public void setBannerUrlAddress(String bannerUrlAddress) {
        this.bannerUrlAddress = bannerUrlAddress;
    }

    @Basic
    @Column(name = "weight", nullable = false, precision = 0)
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "create_banner_time", nullable = false)
    public Timestamp getCreateBannerTime() {
        return createBannerTime;
    }

    public void setCreateBannerTime(Timestamp createBannerTime) {
        this.createBannerTime = createBannerTime;
    }

    @Basic
    @Column(name = "update_banner_time", nullable = false)
    public Timestamp getUpdateBannerTime() {
        return updateBannerTime;
    }

    public void setUpdateBannerTime(Timestamp updateBannerTime) {
        this.updateBannerTime = updateBannerTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        THomeBannerPo that = (THomeBannerPo) o;
        return id == that.id &&
                Double.compare(that.weight, weight) == 0 &&
                Objects.equals(bannerText, that.bannerText) &&
                Objects.equals(bannerPreviewImg, that.bannerPreviewImg) &&
                Objects.equals(bannerUrlAddress, that.bannerUrlAddress) &&
                Objects.equals(createBannerTime, that.createBannerTime) &&
                Objects.equals(updateBannerTime, that.updateBannerTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bannerText, bannerPreviewImg, bannerUrlAddress, weight, createBannerTime, updateBannerTime);
    }
}
