package com.xykj.omservice.user.services;

import com.xykj.omservice.bases.BaseService;
import com.xykj.omservice.user.po.TUserPo;
import org.springframework.stereotype.Service;

@Service
public interface IUserService extends BaseService<TUserPo,Integer> {

    /**
     * 登录
     * @param username
     * @param pwd
     * @return
     */
    TUserPo login(String username,String pwd) throws RuntimeException;

    /**
     * 注册
     * @param registerUser
     * @return
     */
    void register(TUserPo registerUser);


    /**
     * 修改用户信息
     * @param userPo
     * @return
     * @throws RuntimeException
     */
    TUserPo updateInfo(TUserPo userPo) throws RuntimeException;

    void modifyPassWord(int userId,String oldPwd,String newPwd) throws RuntimeException;

}
