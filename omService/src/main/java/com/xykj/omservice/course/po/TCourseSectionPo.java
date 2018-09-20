package com.xykj.omservice.course.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_course_section", schema = "omdb", catalog = "")
public class TCourseSectionPo {
    private int id;
    private String sectionName;
    private int parentId;
    private Integer sequence;
    private int courseId;
    private String duration;
    private String videoUrl;
    private Timestamp createSectionTime;
    private Timestamp updateSectionTime;

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
    @Column(name = "section_name", nullable = false, length = 255)
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @Basic
    @Column(name = "parent_id", nullable = false)
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "sequence", nullable = true)
    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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
    @Column(name = "duration", nullable = true, length = 255)
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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
    @Column(name = "create_section_time", nullable = true)
    public Timestamp getCreateSectionTime() {
        return createSectionTime;
    }

    public void setCreateSectionTime(Timestamp createSectionTime) {
        this.createSectionTime = createSectionTime;
    }

    @Basic
    @Column(name = "update_section_time", nullable = true)
    public Timestamp getUpdateSectionTime() {
        return updateSectionTime;
    }

    public void setUpdateSectionTime(Timestamp updateSectionTime) {
        this.updateSectionTime = updateSectionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TCourseSectionPo that = (TCourseSectionPo) o;
        return id == that.id &&
                parentId == that.parentId &&
                courseId == that.courseId &&
                Objects.equals(sectionName, that.sectionName) &&
                Objects.equals(sequence, that.sequence) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(videoUrl, that.videoUrl) &&
                Objects.equals(createSectionTime, that.createSectionTime) &&
                Objects.equals(updateSectionTime, that.updateSectionTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sectionName, parentId, sequence, courseId, duration, videoUrl, createSectionTime, updateSectionTime);
    }
}
