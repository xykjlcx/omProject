package com.xykj.omservice.user.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_user", schema = "omdb", catalog = "")
public class TUserPo {
    private int id;
    private String userName;
    private String password;
    private String email;
    private String realName;
    private String signature;
    private String phone;
    private String headImg;
    private Integer education;
    private Integer gender;
    private int role;
    private Timestamp lastLoginTime;
    private Timestamp createUserTime;
    private Timestamp updateUserTime;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 30)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "real_name", nullable = true, length = 255)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "signature", nullable = true, length = 255)
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "head_img", nullable = true, length = 255)
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Basic
    @Column(name = "education", nullable = true)
    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    @Basic
    @Column(name = "gender", nullable = true)
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "role", nullable = false)
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Basic
    @Column(name = "last_login_time", nullable = true)
    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Basic
    @Column(name = "create_user_time", nullable = true)
    public Timestamp getCreateUserTime() {
        return createUserTime;
    }

    public void setCreateUserTime(Timestamp createUserTime) {
        this.createUserTime = createUserTime;
    }

    @Basic
    @Column(name = "update_user_time", nullable = true)
    public Timestamp getUpdateUserTime() {
        return updateUserTime;
    }

    public void setUpdateUserTime(Timestamp updateUserTime) {
        this.updateUserTime = updateUserTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TUserPo tUserPo = (TUserPo) o;
        return id == tUserPo.id &&
                role == tUserPo.role &&
                Objects.equals(userName, tUserPo.userName) &&
                Objects.equals(password, tUserPo.password) &&
                Objects.equals(email, tUserPo.email) &&
                Objects.equals(realName, tUserPo.realName) &&
                Objects.equals(signature, tUserPo.signature) &&
                Objects.equals(phone, tUserPo.phone) &&
                Objects.equals(headImg, tUserPo.headImg) &&
                Objects.equals(education, tUserPo.education) &&
                Objects.equals(gender, tUserPo.gender) &&
                Objects.equals(lastLoginTime, tUserPo.lastLoginTime) &&
                Objects.equals(createUserTime, tUserPo.createUserTime) &&
                Objects.equals(updateUserTime, tUserPo.updateUserTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, email, realName, signature, phone, headImg, education, gender, role, lastLoginTime, createUserTime, updateUserTime);
    }
}
