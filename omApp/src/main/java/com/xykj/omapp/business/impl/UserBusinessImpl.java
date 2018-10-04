package com.xykj.omapp.business.impl;

import com.xykj.omapp.business.IUserBusiness;
import com.xykj.omapp.utils.OceanEmial;
import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.course.services.impl.CourseServiceImpl;
import com.xykj.omservice.user.dao.UserDao;
import com.xykj.omservice.user.po.TUserPo;
import com.xykj.omservice.user.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

/**
 * 用户业务实现
 */
@Service
public class UserBusinessImpl implements IUserBusiness {

    @Autowired
    UserServiceImpl userService;
    private Random random = new Random();
    @Autowired
    UserDao userDao;
    @Autowired
    OceanEmial oceanEmial;
    @Autowired
    TemplateEngine templateEngine;

    @Override
    public void register(TUserPo userPo,String hostStr) throws RuntimeException{
        try {
            userService.register(userPo);
            // 可以成功注册后，设置校验码，发送邮件
            String code = "";
            for (int i=0;i<6;i++){
                code += random.nextInt(10);
            }
            // 更新verify_code字段
            code = OceanOperationUtil.md5(code);
            TUserPo update = userDao.findAllById(userPo.getId()).get(0);
            update.setVerifyCode(code);
            userDao.saveAndFlush(update);
            // 激活地址为：host/auth/activate
            // 参数为code(校验码)和name(账户名),均为加密后的结果
            String activeUrl = hostStr + "/auth/activate?code=" + code + "&identifying=" + update.getId();
            Context context = new Context();
            context.setVariable("username",update.getUserName());
            context.setVariable("adminEmail","348686686@qq.com");
            context.setVariable("activeUrl",activeUrl);
            String emailContent = templateEngine.process("registerEmailTemplate",context);
            oceanEmial.sendHtmlEmial(update.getEmail(),"OceanMooc账户激活",emailContent);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("注册失败");
        }
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

    @Override
    public void activate() {
        // 激活
    }

    @Override
    public void forget(String email) {
        List<TUserPo> tUserPoList = userDao.findAllByEmail(email);
        if (OceanOperationUtil.isNullOrEmptyForCollection(tUserPoList)){
            throw new RuntimeException("没有与该邮箱关联的用户！");
        }
        Context context = new Context();
        try {
            TUserPo updateUser = tUserPoList.get(0);
            // 生成一段随机数字
            String newPwd = "";
            int leng = random.nextInt(20);
            for (int i = 0; i < leng; i++) {
                newPwd += random.nextInt(100);
            }
            String entrityNewPwd = OceanOperationUtil.md5(newPwd);
            entrityNewPwd = OceanOperationUtil.handleEncrypt(updateUser.getUserName(),entrityNewPwd);
            updateUser.setPassword(entrityNewPwd);
            updateUser.setUpdateUserTime(new Timestamp(System.currentTimeMillis()));
            userDao.saveAndFlush(updateUser);
            // 发送邮件
            context.setVariable("username",updateUser.getUserName());
            context.setVariable("newpwd",newPwd);
            oceanEmial.sendHtmlEmial(email,"OceanMooc密码重置",templateEngine.process("resetPasswordTemplate",context));
        }catch (Exception e){
            throw new RuntimeException("未知错误，不能重置密码");
        }
    }
}
