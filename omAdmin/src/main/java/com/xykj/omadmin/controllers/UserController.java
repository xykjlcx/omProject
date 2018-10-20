package com.xykj.omadmin.controllers;

import com.alibaba.fastjson.JSONObject;
import com.xykj.omadmin.business.IUserBusiness;
import com.xykj.omadmin.business.impl.UserBusinessImpl;
import com.xykj.omadmin.vo.UserVoAdmin;
import com.xykj.ombase.returnformat.OceanReturn;
import com.xykj.ombase.returnformat.Result;
import com.xykj.ombase.utils.redis.IRedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ocean
 * @Title: UserController
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/19上午9:56
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    IUserBusiness userBusiness;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody JSONObject jsonObject){
        String account = "";
        String password = "";
        try {
            account = jsonObject.getString("account");
            password = jsonObject.getString("password");
            System.out.println("-----------account:" + account + " | password" + password + "---------");
            UserVoAdmin userVoAdmin = userBusiness.loginAdmin(account,password);
            if (userVoAdmin != null){
                return OceanReturn.successResult(
                        "登录成功",
                        userVoAdmin
                );
            }else {
                return OceanReturn.errorResult(
                        "登录失败",
                        null
                );
            }
        }catch (RuntimeException e){
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }
    }

    @RequestMapping(value = "/checkLogin",method = RequestMethod.POST)
    public Result checkLogin(@RequestBody JSONObject jsonObject){
       try {
           String tokenStr = jsonObject.getString("token");
           System.out.println("-----------token:" + tokenStr +  "---------");
           boolean isLogin = userBusiness.isLoginAdmin(tokenStr);
           if (isLogin){
               return OceanReturn.successResult(
                       "用户已登录",
                       isLogin
               );
           }else {
               return OceanReturn.errorResult(
                       "用户未登录",
                       isLogin
               );
           }
       }catch (Exception e){
           e.printStackTrace();
           System.err.println("检测管理员是否登录异常");
           return OceanReturn.errorResult(
                   "检测异常",
                   null
           );
       }
    }


    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public Result logout(@RequestBody JSONObject jsonObject){
        String tokenStr = jsonObject.getString("token");
        System.out.println("-----------token:" + tokenStr +  "---------");
        int result = userBusiness.logoutAdmin(tokenStr);
        if (result == 1){
            return OceanReturn.successResult(
                    "退出登录成功",
                    null
            );
        }else {
            return OceanReturn.successResult(
                    "退出登录失败",
                    null
            );
        }
    }

}
