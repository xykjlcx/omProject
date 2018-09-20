package com.xykj.omservice.user.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_user_course_collect", schema = "omdb", catalog = "")
public class TUserCourseCollectPo {
    private int id;
    private int userId;
    private int courseId;
    private Timestamp collectTime;

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
    @Column(name = "collect_time", nullable = false)
    public Timestamp getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Timestamp collectTime) {
        this.collectTime = collectTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TUserCourseCollectPo that = (TUserCourseCollectPo) o;
        return id == that.id &&
                userId == that.userId &&
                courseId == that.courseId &&
                Objects.equals(collectTime, that.collectTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, courseId, collectTime);
    }
}
