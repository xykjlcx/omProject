package com.xykj.omapp.controller;

import com.xykj.omapp.business.impl.CourseBusinessImpl;
import com.xykj.omapp.utils.PoConvertVo;
import com.xykj.ombase.returnformat.OceanReturn;
import com.xykj.ombase.returnformat.Result;
import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.user.po.TUserPo;
import com.xykj.omservice.user.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    CourseBusinessImpl courseBusiness;
    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/login")
    public Result login(@RequestParam("account") String account,
                        @RequestParam("password") String pwd){
        try {
            String encrypt = OceanOperationUtil.md5(OceanOperationUtil.md5(account) + pwd);
            TUserPo tUserPo = userService.login(account,encrypt);
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

    @ResponseBody
    @RequestMapping("/init")
    public Result index(){
        return OceanReturn
                .successResult(
                        "查询所有课程成功",
//                        courseBusiness.findAllCourse()
                        "qwe"
                );

    }

}
