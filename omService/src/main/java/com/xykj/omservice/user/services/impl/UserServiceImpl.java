package com.xykj.omservice.user.services.impl;

import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.user.dao.UserDao;
import com.xykj.omservice.user.po.TUserPo;
import com.xykj.omservice.user.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ocean
 * @Title: UserServiceImpl
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/3下午5:49
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserDao userDao;

    @Override
    public TUserPo login(String username, String encrypt) throws Exception {
        List<TUserPo> tUserPoList = userDao.findAllByUserName(username);
        if (OceanOperationUtil.isNullOrEmptyForCollection(tUserPoList)){
            throw new RuntimeException("登录失败，该用户不存在");
        }
        String dbPwd = tUserPoList.get(0).getPassword();
        if (!dbPwd.equals(encrypt)){
            throw new RuntimeException("登录失败，密码错误");
        }
        return tUserPoList.get(0);
    }

    @Override
    public void save(TUserPo data) throws Exception {

    }

    @Override
    public void deleteById(Integer integer) throws Exception {

    }

    @Override
    public List<TUserPo> findAll() {
        return null;
    }

    @Override
    public TUserPo findById() {
        return null;
    }
}
