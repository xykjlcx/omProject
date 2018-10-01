package com.xykj.omservice.notices.po;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author ocean
 * @Title: TNoticesPo
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/9/30下午7:09
 */
@Entity
@Table(name = "t_notices", schema = "omdb", catalog = "")
public class TNoticesPo {
    private Integer id;
    private String contentOne;
    private String contentTwo;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content_one")
    public String getContentOne() {
        return contentOne;
    }

    public void setContentOne(String contentOne) {
        this.contentOne = contentOne;
    }

    @Basic
    @Column(name = "content_two")
    public String getContentTwo() {
        return contentTwo;
    }

    public void setContentTwo(String contentTwo) {
        this.contentTwo = contentTwo;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TNoticesPo that = (TNoticesPo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (contentOne != null ? !contentOne.equals(that.contentOne) : that.contentOne != null) return false;
        if (contentTwo != null ? !contentTwo.equals(that.contentTwo) : that.contentTwo != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (contentOne != null ? contentOne.hashCode() : 0);
        result = 31 * result + (contentTwo != null ? contentTwo.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
