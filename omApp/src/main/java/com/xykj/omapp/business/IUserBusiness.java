package com.xykj.omapp.business;

import com.xykj.omservice.user.po.TIdeaBackPo;
import com.xykj.omservice.user.po.TUserPo;
import org.springframework.stereotype.Service;

@Service
public interface IUserBusiness {

    /**
     * 注册
     * @param userPo
     */
    void register(TUserPo userPo,String hostStr);

    /**
     * 登录
     * @param username
     * @param Password
     * @return
     */
    TUserPo login(String username,String Password);

    /**
     * 登出
     * @param username
     * @return
     */
    boolean logout(String username);

    /**
     * 修改密码
     * @param username
     * @param old_pwd
     * @param new_pwd
     * @return
     */
    boolean alterPassWord(String username,String old_pwd,String new_pwd);

    /**
     * 修改个人信息
     * @param newuserinfo
     * @return
     */
    TUserPo alterUserInfo(TUserPo newuserinfo);


    /**
     * 激活
     */
    void activate();

    /**
     * 忘记密码
     * @param email
     */
    void forget(String email);

    void addIdeaBack(TIdeaBackPo ideaBackPo) throws RuntimeException;

}
