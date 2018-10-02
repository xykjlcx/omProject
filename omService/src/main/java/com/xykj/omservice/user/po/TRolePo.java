package com.xykj.omservice.user.po;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author ocean
 * @Title: TRolePo
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/9/30下午12:34
 */
@Entity
@Table(name = "t_role", schema = "omdb", catalog = "")
public class TRolePo {
    private int id;
    private String roleName;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_name", nullable = false, length = 255)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
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

        TRolePo tRolePo = (TRolePo) o;

        if (id != tRolePo.id) return false;
        if (roleName != null ? !roleName.equals(tRolePo.roleName) : tRolePo.roleName != null) return false;
        if (createTime != null ? !createTime.equals(tRolePo.createTime) : tRolePo.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(tRolePo.updateTime) : tRolePo.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TRolePo{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
