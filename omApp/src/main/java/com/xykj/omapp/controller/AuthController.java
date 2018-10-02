package com.xykj.omapp.controller;

import com.xykj.omapp.business.impl.CourseBusinessImpl;
import com.xykj.ombase.returnformat.OceanReturn;
import com.xykj.ombase.returnformat.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    CourseBusinessImpl courseBusiness;

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
