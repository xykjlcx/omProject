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
    TUserPo login(String username,String pwd) throws Exception;

    /**
     * 注册
     * @param registerUser
     * @return
     */
    void register(TUserPo registerUser);


}
