package com.xykj.omapp.business.impl;

import com.xykj.omapp.business.IUserBusiness;
import com.xykj.omservice.course.services.impl.CourseServiceImpl;
import com.xykj.omservice.user.po.TUserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务实现
 */
@Service
public class UserBusinessImpl implements IUserBusiness {


    @Override
    public boolean register(TUserPo userPo) {
        return false;
    }

    @Override
    public TUserPo login(String username, String Password) {
        return null;
    }

    @Override
    public boolean logout(String username) {
        return false;
    }

    @Override
    public boolean alterPassWord(String username, String old_pwd, String new_pwd) {
        return false;
    }

    @Override
    public TUserPo alterUserInfo(TUserPo newuserinfo) {
        return null;
    }
}
