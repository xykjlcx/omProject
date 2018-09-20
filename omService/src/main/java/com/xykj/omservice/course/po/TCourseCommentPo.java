package com.xykj.omservice.course.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_course_comment", schema = "omdb", catalog = "")
public class TCourseCommentPo {
    private int id;
    private String commentContent;
    private int userId;
    private int courseId;
    private int sectionId;
    private Timestamp createCommentTime;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "comment_content", nullable = false, length = 255)
    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
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
    @Column(name = "section_id", nullable = false)
    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    @Basic
    @Column(name = "create_comment_time", nullable = false)
    public Timestamp getCreateCommentTime() {
        return createCommentTime;
    }

    public void setCreateCommentTime(Timestamp createCommentTime) {
        this.createCommentTime = createCommentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TCourseCommentPo that = (TCourseCommentPo) o;
        return id == that.id &&
                userId == that.userId &&
                courseId == that.courseId &&
                sectionId == that.sectionId &&
                Objects.equals(commentContent, that.commentContent) &&
                Objects.equals(createCommentTime, that.createCommentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commentContent, userId, courseId, sectionId, createCommentTime);
    }
}
