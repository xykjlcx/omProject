package com.xykj.omapp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVo {

    private Integer id;
    private String account;
    private String email;
    private String realName;
    private String signature;
    private String phone;
    private String headImg;
    /**
     * 学历
     * 0 专科
     * 1 本科
     * 2 硕士
     * 3 博士
     * 4 其他
     */
    private int education;
    /**
     * 性别
     * 0 男
     * 1 女
     */
    private int gender;     // 性别：0男/1女
    private String lastLoginTime;   // 最后登录时间

}
