package com.xykj.omservice.user.services.impl;

import com.xykj.ombase.utils.OceanDateUtil;
import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.OmConstant;
import com.xykj.omservice.user.dao.UserDao;
import com.xykj.omservice.user.po.TUserPo;
import com.xykj.omservice.user.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

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
    private Random random = new Random();

    @Override
    public TUserPo login(String username, String pwd) throws Exception {
        List<TUserPo> tUserPoList = userDao.findAllByUserName(username);
        if (OceanOperationUtil.isNullOrEmptyForCollection(tUserPoList)){
            throw new RuntimeException("登录失败，用户不存在");
        }
        // 二次加密后校对
        String encrypt = OceanOperationUtil.handleEncrypt(username,pwd);
        String dbPwd = tUserPoList.get(0).getPassword();
        if (!dbPwd.equals(encrypt)){
            throw new RuntimeException("登录失败，密码错误");
        }
        if (tUserPoList.get(0).getStatus() == 0){
            throw new RuntimeException("登录失败，账户暂不可用！");
        }
        // 登录成功
        tUserPoList.get(0).setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        userDao.saveAndFlush(tUserPoList.get(0));
        return tUserPoList.get(0);
    }

    @Override
    public void register(TUserPo registerUser) {
        List<TUserPo> checkIsExistList = userDao.findAllByUserName(registerUser.getUserName());
        if (OceanOperationUtil.isNotNullOrEmptyForCollection(checkIsExistList)){
            throw new RuntimeException("注册失败，该账户已存在");
        }
        checkIsExistList = userDao.findAllByEmail(registerUser.getEmail());
        if (OceanOperationUtil.isNotNullOrEmptyForCollection(checkIsExistList)){
            throw new RuntimeException("注册失败，该邮箱已被注册");
        }
        // 设置默认值
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        registerUser.setPassword(OceanOperationUtil.handleEncrypt(registerUser.getUserName(),registerUser.getPassword()));
        registerUser.setRealName(OmConstant.UserPropertyDefault.REAL_NAME);
        registerUser.setSignature(OmConstant.UserPropertyDefault.SIGNATURE);
        registerUser.setPhone(OmConstant.UserPropertyDefault.PHONE);
        registerUser.setHeadImg(OmConstant.UserPropertyDefault.HEAD_IMG);
        registerUser.setEducation(OmConstant.UserPropertyDefault.EDUCATION);
        registerUser.setGender(OmConstant.UserPropertyDefault.GENDER);
        registerUser.setCreateUserTime(nowTime);
        registerUser.setUpdateUserTime(nowTime);
        registerUser.setLastLoginTime(nowTime);
        registerUser.setStatus(OmConstant.UserPropertyDefault.STATUS);
        registerUser.setVerifyCode(OmConstant.UserPropertyDefault.VERIFY_CODE);
        userDao.save(registerUser);
    }



    @Override
    public void save(TUserPo data) throws Exception {
        if (data != null){
            userDao.saveAndFlush(data);
        }
    }

    @Override
    public void deleteById(Integer integer) throws Exception {

    }

    @Override
    public List<TUserPo> findAll() {
        return null;
    }

    @Override
    public TUserPo findById(Integer id) throws RuntimeException {
        List<TUserPo> tUserPoList = userDao.findAllById(id);
        if (OceanOperationUtil.isNullOrEmptyForCollection(tUserPoList)){
            throw new RuntimeException("查询不到用户");
        }
        return tUserPoList.get(0);
    }
}
