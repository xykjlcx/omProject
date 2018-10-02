package com.xykj.omservice.user.po;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user", schema = "omdb", catalog = "")
public class TUserPo {
    /**
     * 用户自增id
     */
    @Id@Column(name = "id", nullable = false)@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * 登录用户名
     */
    @Basic@Column(name = "user_name", nullable = false, length = 30)
    private String userName;
    /**
     * 登录密码
     */
    @Basic@Column(name = "password", nullable = false, length = 30)
    private String password;
    /**
     * 邮箱地址
     */
    @Basic@Column(name = "email", nullable = false, length = 255)
    private String email;
    /**
     * 真实姓名
     */
    @Basic@Column(name = "real_name", nullable = true, length = 255)
    private String realName;
    /**
     * 个性签名
     */
    @Basic@Column(name = "signature", nullable = true, length = 255)
    private String signature;
    /**
     * 手机号码
     */
    @Basic@Column(name = "phone", nullable = true, length = 20)
    private String phone;
    /**
     * 头像地址
     */
    @Basic@Column(name = "head_img", nullable = true, length = 255)
    private String headImg;
    /**
     * 学校
     */
    @Basic@Column(name = "education", nullable = true)
    private Integer education;
    /**
     * 性别
     */
    @Basic@Column(name = "gender", nullable = true)
    private Integer gender;
    /**
     * 最近一次登录时间
     */
    @Basic@Column(name = "last_login_time", nullable = true)
    private Timestamp lastLoginTime;
    /**
     * 注册时间
     */
    @Basic@Column(name = "create_user_time", nullable = true)
    private Timestamp createUserTime;
    /**
     * 更新用户信息之间
     */
    @Basic@Column(name = "update_user_time", nullable = true)
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

        if (id != tUserPo.id) return false;
        if (userName != null ? !userName.equals(tUserPo.userName) : tUserPo.userName != null) return false;
        if (password != null ? !password.equals(tUserPo.password) : tUserPo.password != null) return false;
        if (email != null ? !email.equals(tUserPo.email) : tUserPo.email != null) return false;
        if (realName != null ? !realName.equals(tUserPo.realName) : tUserPo.realName != null) return false;
        if (signature != null ? !signature.equals(tUserPo.signature) : tUserPo.signature != null) return false;
        if (phone != null ? !phone.equals(tUserPo.phone) : tUserPo.phone != null) return false;
        if (headImg != null ? !headImg.equals(tUserPo.headImg) : tUserPo.headImg != null) return false;
        if (education != null ? !education.equals(tUserPo.education) : tUserPo.education != null) return false;
        if (gender != null ? !gender.equals(tUserPo.gender) : tUserPo.gender != null) return false;
        if (lastLoginTime != null ? !lastLoginTime.equals(tUserPo.lastLoginTime) : tUserPo.lastLoginTime != null)
            return false;
        if (createUserTime != null ? !createUserTime.equals(tUserPo.createUserTime) : tUserPo.createUserTime != null)
            return false;
        if (updateUserTime != null ? !updateUserTime.equals(tUserPo.updateUserTime) : tUserPo.updateUserTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (realName != null ? realName.hashCode() : 0);
        result = 31 * result + (signature != null ? signature.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (headImg != null ? headImg.hashCode() : 0);
        result = 31 * result + (education != null ? education.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (lastLoginTime != null ? lastLoginTime.hashCode() : 0);
        result = 31 * result + (createUserTime != null ? createUserTime.hashCode() : 0);
        result = 31 * result + (updateUserTime != null ? updateUserTime.hashCode() : 0);
        return result;
    }
}
