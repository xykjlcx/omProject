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
    /**
     * 用户状态
     * 0 未激活
     * 1 激活
     */
    @Basic@Column(name = "status",nullable = true)
    private int status;
    /**
     * 用于存储各种校验码
     * 1. 注册时激活邮件
     * 2. 找回(重置)密码时验证
     */
    @Basic@Column(name = "verify_code",nullable = true)
    private String verifyCode;

}
