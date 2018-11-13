package com.xykj.omadmin.controllers;

import com.alibaba.fastjson.JSONObject;
import com.xykj.omadmin.business.IUserBusiness;
import com.xykj.omadmin.vo.UserVoAdmin;
import com.xykj.ombase.returnformat.OceanReturn;
import com.xykj.ombase.returnformat.Result;
import com.xykj.omservice.user.po.TUserPo;
import com.xykj.omservice.user.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ocean
 * @Title: UserController
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/19上午9:56
 */
@RestController
@RequestMapping("/api/admin/user")
public class UserController {

    @Autowired
    IUserBusiness userBusiness;
    @Autowired
    IUserService userService;

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


    /**
     * 获取用户列表
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/getAllUserInfo",method = RequestMethod.POST)
    public Result getAllUserInfo(@RequestBody JSONObject jsonObject){
        try {
            int page = jsonObject.getInteger("page");
            int size = jsonObject.getInteger("size");
            int status = jsonObject.getInteger("status");
            Map<String,Object> data = userBusiness.getAllUserInfo(status,page,size);
            return OceanReturn.successResult(
                    "获取用户列表成功",
                    data
            );
        }catch (Exception e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    "获取用户列表失败",
                    null
            );
        }
    }



    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public Result deleteUser(@RequestBody JSONObject jsonObject) {
       try {
           int userId = jsonObject.getInteger("userId");
           userService.deleteUserById(userId);
           // 删除成功后，清除redis缓存

           return OceanReturn.successResult(
                   "删除成功",
                   null
           );
       }catch (RuntimeException e){
           e.printStackTrace();
           return OceanReturn.errorResult(
                   e.getMessage(),
                   null
           );
       }catch (Exception e){
           e.printStackTrace();
           return OceanReturn.errorResult(
                   "删除失败，未知异常",
                   null
           );
       }
    }

    /**
     * 操作用户状态禁用/可用
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/operateUserStatus",method = RequestMethod.POST)
    public Result operateUserStatus(@RequestBody JSONObject jsonObject) {
        Integer userId = null;
        Boolean isBan = null;
        try {
            userId = jsonObject.getInteger("userId");
            isBan = jsonObject.getBoolean("isBan");
            userService.operateUserStatus(userId,isBan);
            return OceanReturn.successResult(
                    "操作成功",
                    null
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }catch (Exception e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    "操作失败",
                    null
            );
        }
    }

}
