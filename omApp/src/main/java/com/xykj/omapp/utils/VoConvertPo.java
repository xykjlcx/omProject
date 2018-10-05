package com.xykj.omapp.utils;

import com.xykj.omapp.vo.UserVo;
import com.xykj.omservice.user.po.TUserPo;

import java.sql.Timestamp;

/**
 * @author ocean
 * @Title: VoConvertPo
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/6上午12:05
 */
public class VoConvertPo {

    public static TUserPo convert(UserVo userVo){
        return TUserPo.builder()
                .id(userVo.getId())
                .userName(userVo.getAccount())
                .realName(userVo.getRealName())
                .signature(userVo.getSignature())
                .education(userVo.getEducation())
                .gender(userVo.getGender())
                .email(userVo.getEmail())
                .updateUserTime(new Timestamp(System.currentTimeMillis()))
                .build();
    }

}
