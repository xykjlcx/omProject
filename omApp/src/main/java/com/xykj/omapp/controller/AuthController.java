package com.xykj.omapp.controller;

import com.xykj.omapp.business.impl.CourseBusinessImpl;
import com.xykj.omapp.business.impl.UserBusinessImpl;
import com.xykj.omapp.utils.AppUtil;
import com.xykj.omapp.utils.PoConvertVo;
import com.xykj.omapp.utils.VoConvertPo;
import com.xykj.omapp.vo.CourseVo;
import com.xykj.omapp.vo.UserVo;
import com.xykj.ombase.returnformat.OceanReturn;
import com.xykj.ombase.returnformat.Result;
import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.user.dao.UserDao;
import com.xykj.omservice.user.po.TUserPo;
import com.xykj.omservice.user.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    CourseBusinessImpl courseBusiness;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserBusinessImpl userBusiness;
    @Autowired
    TemplateEngine templateEngine;

    @RequestMapping("/login")
    public Result login(@RequestParam("account") String account,
                        @RequestParam("password") String pwd){
        try {
            TUserPo tUserPo = userService.login(account,pwd);
            return OceanReturn.successResult(
                    "登录成功",
                    PoConvertVo.convert(tUserPo)
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
                    "登录失败",
                    null
            );
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result register(@RequestParam("account") String account,
                           @RequestParam("password") String pwd,
                           @RequestParam("email") String email,
                           HttpServletRequest request){
        TUserPo registerUser = new TUserPo();
        registerUser.setUserName(account);
        registerUser.setPassword(pwd);
        registerUser.setEmail(email);
        String hostUrl = AppUtil.getUrlHost(request);
        try {
            userBusiness.register(registerUser,hostUrl);
            return OceanReturn.successResult(
                    "注册成功,请等待邮件激活！",
                    null
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }
    }


    @RequestMapping(value = "/activate",method = RequestMethod.GET)
    public String activate(@RequestParam(value = "code",required = false) String code,
                           @RequestParam(value = "identifying",required = false) int userId){
        // 可以从url获取?name=value的参数
        TUserPo userPo = null;
        String checkCode = null;
        Context context = null;
        try {
            userPo = userService.findById(userId);
            checkCode = userPo.getVerifyCode();
            context = new Context();
            if (checkCode.equals("-1")) {
                // 激活成功后将值设置为-1表示已激活
                context.setVariable("account",userPo.getUserName());
                context.setVariable("result","当前账户已激活，不可重复激活！");
                return templateEngine.process("resultTemplate",context);
            }
            if (code.equals(checkCode)){
                // 验证成功
                userPo.setStatus(1);
                userPo.setVerifyCode("-1");
                userService.save(userPo);
                context.setVariable("account",userPo.getUserName());
                context.setVariable("result","恭喜您，账户已成功激活，赶快去登录吧！");
            }else {
                context.setVariable("account",userPo.getUserName());
                context.setVariable("result","Sorry，账户激活失败。请联系管理员！");
            }
            return templateEngine.process("resultTemplate",context);
        }catch (RuntimeException e){
            e.printStackTrace();
            context.setVariable("account",userPo.getUserName());
            context.setVariable("result","Sorry，账户激活失败：" + e.getMessage() + "。请联系管理员！");
            return templateEngine.process("resultTemplate",context);
        }catch (Exception e){
            e.printStackTrace();
            context.setVariable("account",userPo.getUserName());
            context.setVariable("result","Sorry，账户激活失败：" + e.getMessage() + "。请联系管理员！");
            return templateEngine.process("resultTemplate",context);
        }

    }

    @RequestMapping(value = "/forget",method = RequestMethod.POST)
    public Result forget(@RequestParam("email") String email){
        // 忘记密码 -> 发送邮件 -> 获取重置后的密码
        try {
            if (!email.equals("")){
                userBusiness.forget(email);
            }
            return OceanReturn.successResult(
                    "重置密码成功，稍后以邮件形式发送新密码",
                    null
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }
    }

    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    public Result getUserInfo(@RequestParam("userId") int userId){
        try {
            TUserPo checkUser = userService.findById(userId);
            return OceanReturn.successResult(
                    "获取用户信息成功",
                    PoConvertVo.convert(checkUser)
            );
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }
    }

    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public Result updateUserInfo(UserVo userVo){
        try {
            if (userVo != null){
                TUserPo userPo = VoConvertPo.convert(userVo);
                UserVo resultVo = PoConvertVo.convert(userService.updateInfo(userPo));
                return OceanReturn.successResult(
                        "用户资料更新成功",
                        resultVo
                );
            }else {
                return OceanReturn.errorResult(
                        "传参有误",
                        null
                );
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }
    }

    @RequestMapping(value = "/modifyUserPwd",method = RequestMethod.POST)
    public Result modifyUserPwd(@RequestParam("userId") int userId,
                                @RequestParam("oldPwd") String oldPwd,
                                @RequestParam("newPwd") String newPwd){
        try {
            userService.modifyPassWord(userId,oldPwd,newPwd);
            return OceanReturn.successResult(
                    "密码修改成功,请重新登录",
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
                    "修改失败，未知错误",
                    null
            );
        }
    }

}
