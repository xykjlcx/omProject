package com.xykj.omservice.course.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_course", schema = "omdb", catalog = "")
public class TCoursePo {
    private int id;
    private String courseName;
    private String courseDesc;
    private int classifyId;
    private String duration;
    private String level;
    private String previewImg;
    private String videoUrl;
    private Timestamp createCourseTime;
    private Timestamp updateCourseTime;
    private Integer isPutaway;
    private Integer weight;
    private Integer isFree;
    private Integer price;

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
    @Column(name = "course_name", nullable = false, length = 255)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "course_desc", nullable = false, length = 255)
    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    @Basic
    @Column(name = "classify_id", nullable = false)
    public int getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(int classifyId) {
        this.classifyId = classifyId;
    }

    @Basic
    @Column(name = "duration", nullable = true, length = 255)
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "level", nullable = true, length = 255)
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Basic
    @Column(name = "preview_img", nullable = false, length = 255)
    public String getPreviewImg() {
        return previewImg;
    }

    public void setPreviewImg(String previewImg) {
        this.previewImg = previewImg;
    }

    @Basic
    @Column(name = "video_url", nullable = true, length = 255)
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Basic
    @Column(name = "create_course_time", nullable = true)
    public Timestamp getCreateCourseTime() {
        return createCourseTime;
    }

    public void setCreateCourseTime(Timestamp createCourseTime) {
        this.createCourseTime = createCourseTime;
    }

    @Basic
    @Column(name = "update_course_time", nullable = true)
    public Timestamp getUpdateCourseTime() {
        return updateCourseTime;
    }

    public void setUpdateCourseTime(Timestamp updateCourseTime) {
        this.updateCourseTime = updateCourseTime;
    }

    @Basic
    @Column(name = "is_putaway", nullable = true)
    public Integer getIsPutaway() {
        return isPutaway;
    }

    public void setIsPutaway(Integer isPutaway) {
        this.isPutaway = isPutaway;
    }

    @Basic
    @Column(name = "weight", nullable = true)
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "is_free", nullable = true)
    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    @Basic
    @Column(name = "price", nullable = true)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TCoursePo tCoursePo = (TCoursePo) o;
        return id == tCoursePo.id &&
                classifyId == tCoursePo.classifyId &&
                Objects.equals(courseName, tCoursePo.courseName) &&
                Objects.equals(courseDesc, tCoursePo.courseDesc) &&
                Objects.equals(duration, tCoursePo.duration) &&
                Objects.equals(level, tCoursePo.level) &&
                Objects.equals(previewImg, tCoursePo.previewImg) &&
                Objects.equals(videoUrl, tCoursePo.videoUrl) &&
                Objects.equals(createCourseTime, tCoursePo.createCourseTime) &&
                Objects.equals(updateCourseTime, tCoursePo.updateCourseTime) &&
                Objects.equals(isPutaway, tCoursePo.isPutaway) &&
                Objects.equals(weight, tCoursePo.weight) &&
                Objects.equals(isFree, tCoursePo.isFree) &&
                Objects.equals(price, tCoursePo.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, courseDesc, classifyId, duration, level, previewImg, videoUrl, createCourseTime, updateCourseTime, isPutaway, weight, isFree, price);
    }
}
