package com.xykj.omadmin.business.impl;

import com.xykj.omadmin.business.IUserBusiness;
import com.xykj.omadmin.utils.AdminConstant;
import com.xykj.omadmin.utils.jwt.JwtUtil;
import com.xykj.omadmin.utils.jwt.UserToken;
import com.xykj.omadmin.vo.UserVoAdmin;
import com.xykj.ombase.utils.redis.IRedisClient;
import com.xykj.omservice.user.po.TUserPo;
import com.xykj.omservice.user.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ocean
 * @Title: UserBusinessImpl
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/19上午10:16
 */
@Service
public class UserBusinessImpl implements IUserBusiness {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    IRedisClient redisClient;

    @Override
    public UserVoAdmin loginAdmin(String username, String password) {
        try {
            TUserPo userPo = userService.login(username,password);
            if (userPo != null){
                UserToken userToken = new UserToken(userPo.getId(),userPo.getUserName());
                String tokenStr = JwtUtil.generateToken(userToken,JwtUtil.EXPIRE_TIME);
                // 将token存到redis中
                redisClient.set(AdminConstant.SESSION  + tokenStr,tokenStr);
                return UserVoAdmin.builder()
                        .id(userPo.getId())
                        .userName(userPo.getUserName())
                        .email(userPo.getEmail())
                        .gender(userPo.getGender())
                        .realName(userPo.getRealName())
                        .token(tokenStr)
                        .avator(userPo.getHeadImg())
                        .signature(userPo.getSignature())
                        .build();
            }
        }catch (RuntimeException e){
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token生成失败");
        }
        return null;
    }

    @Override
    public boolean isLoginAdmin(String token) {
        String resultToken = redisClient.get(AdminConstant.SESSION + token);
        if (resultToken == null){
            // 未登录
            return false;
        }else {
            // 已登录
            return true;
        }
    }

    @Override
    public int logoutAdmin(String token) {
        redisClient.del(AdminConstant.SESSION + token);
        // 表示删除token成功
        return 1;
    }
}
