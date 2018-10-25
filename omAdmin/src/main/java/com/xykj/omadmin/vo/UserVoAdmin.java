package com.xykj.omadmin.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ocean
 * @Title: UserVoAdmin
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/19上午10:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVoAdmin {

    private Integer id;
    private Integer dbId;
    private String userName;
    private String email;
    private String realName;
    private String signature;
    private int gender;
    private String genderStr;
    private String token;
    private String avator;
    private String education;
    private int status;
    private boolean statusBool;

}
