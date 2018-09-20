package com.xykj.omservice.course.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_course_classify", schema = "omdb", catalog = "")
public class TCourseClassifyPo {
    private int id;
    private String classifyName;
    private Integer parentId;
    private String sequence;
    private Timestamp createClassifyTime;
    private Timestamp updateClassifyTime;

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
    @Column(name = "classify_name", nullable = false, length = 255)
    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    @Basic
    @Column(name = "parent_id", nullable = true)
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "sequence", nullable = true, length = 255)
    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Basic
    @Column(name = "create_classify_time", nullable = true)
    public Timestamp getCreateClassifyTime() {
        return createClassifyTime;
    }

    public void setCreateClassifyTime(Timestamp createClassifyTime) {
        this.createClassifyTime = createClassifyTime;
    }

    @Basic
    @Column(name = "update_classify_time", nullable = true)
    public Timestamp getUpdateClassifyTime() {
        return updateClassifyTime;
    }

    public void setUpdateClassifyTime(Timestamp updateClassifyTime) {
        this.updateClassifyTime = updateClassifyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TCourseClassifyPo that = (TCourseClassifyPo) o;
        return id == that.id &&
                Objects.equals(classifyName, that.classifyName) &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(sequence, that.sequence) &&
                Objects.equals(createClassifyTime, that.createClassifyTime) &&
                Objects.equals(updateClassifyTime, that.updateClassifyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classifyName, parentId, sequence, createClassifyTime, updateClassifyTime);
    }
}
