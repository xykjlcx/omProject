package com.xykj.omadmin.utils.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ocean
 * @Title: UserToken
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/19上午10:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserToken {

    private int userId;
    private String username;

}
