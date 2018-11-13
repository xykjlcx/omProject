package com.xykj.omadmin.controllers;

import com.xykj.omadmin.business.impl.FileOperateBusinessImpl;
import com.xykj.omadmin.utils.AliOssStorage;
import com.xykj.ombase.returnformat.OceanReturn;
import com.xykj.ombase.returnformat.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author ocean
 * @Title: MainController
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/13下午5:59
 */
@RestController
@RequestMapping("/api/admin/main")
public class MainController {

    @Autowired
    FileOperateBusinessImpl fileOperateBusiness;

    /**
     * 文件上传接口
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Result upload(MultipartFile file) {
        try {
            String uploadUrl = fileOperateBusiness.upload(file);
            return OceanReturn.successResult(
                    "上传成功",
                    uploadUrl
            );
        } catch (Exception e) {
            e.printStackTrace();
            return OceanReturn.errorResult(
                    e.getMessage(),
                    null
            );
        }
    }

}
