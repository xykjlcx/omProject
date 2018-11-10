package com.xykj.omadmin.controllers;

import com.xykj.omadmin.business.IHomeBusiness;
import com.xykj.ombase.returnformat.OceanReturn;
import com.xykj.ombase.returnformat.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ocean
 * @Title: HomeController
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/11/69:21 AM
 */
@RestController
@RequestMapping("/admin/home")
public class HomeController {

    @Autowired
    IHomeBusiness homeBusiness;

    @RequestMapping(value = "/getHomeData",method = RequestMethod.GET)
    public Result getHomeData() {
        try {
            return OceanReturn.successResult(
                    "获取首页数据成功",
                    homeBusiness.getHomeData()
            );
        }catch (Exception e){
            e.printStackTrace();
            return OceanReturn.errorResult(
                    "获取数据失败",
                    null
            );
        }
    }

}
