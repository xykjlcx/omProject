package com.xykj.omservice.user.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_user_course_study", schema = "omdb", catalog = "")
public class TUserCourseStudyPo {
    private int id;
    private int userId;
    private int courseId;
    private Integer sectionId;
    private Timestamp firstStudyTime;
    private Timestamp lastStudyTime;
    private Timestamp createTime;
    private Integer status;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "course_id", nullable = false)
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "section_id", nullable = true)
    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    @Basic
    @Column(name = "first_study_time", nullable = true)
    public Timestamp getFirstStudyTime() {
        return firstStudyTime;
    }

    public void setFirstStudyTime(Timestamp firstStudyTime) {
        this.firstStudyTime = firstStudyTime;
    }

    @Basic
    @Column(name = "last_study_time", nullable = true)
    public Timestamp getLastStudyTime() {
        return lastStudyTime;
    }

    public void setLastStudyTime(Timestamp lastStudyTime) {
        this.lastStudyTime = lastStudyTime;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TUserCourseStudyPo that = (TUserCourseStudyPo) o;
        return id == that.id &&
                userId == that.userId &&
                courseId == that.courseId &&
                Objects.equals(sectionId, that.sectionId) &&
                Objects.equals(firstStudyTime, that.firstStudyTime) &&
                Objects.equals(lastStudyTime, that.lastStudyTime) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, courseId, sectionId, firstStudyTime, lastStudyTime, createTime, status);
    }
}
