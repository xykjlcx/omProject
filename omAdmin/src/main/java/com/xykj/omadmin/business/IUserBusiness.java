package com.xykj.omadmin.business;

import com.xykj.omadmin.vo.UserVoAdmin;
import org.springframework.stereotype.Service;

/**
 * @author ocean
 * @Title: IUserBusiness
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/19上午10:14
 */
@Service
public interface IUserBusiness {

    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    UserVoAdmin loginAdmin(String username, String password);

    /**
     * 检测登录状态
     * @param token
     * @return
     */
    boolean isLoginAdmin(String token);

    /**
     * 退出登录
     * @param token
     * @return
     */
    int logoutAdmin(String token);

}
