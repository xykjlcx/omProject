package com.xykj.omadmin.business.impl;

import com.xykj.omadmin.business.IFileOperateBusiness;
import com.xykj.omadmin.utils.AliOssStorage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author ocean
 * @Title: FileOperateBusinessImpl
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/13下午6:06
 */
@Service
public class FileOperateBusinessImpl implements IFileOperateBusiness {

    public String uploadAiOSS(MultipartFile file){
        String uploadUrl = "";
        try {
            File newFile = new File(file.getOriginalFilename());
            FileOutputStream os = new FileOutputStream(newFile);
            os.write(file.getBytes());
            os.close();
            file.transferTo(newFile);
            //上传到OSS
            uploadUrl = AliOssStorage.upload(newFile);
            if (!uploadUrl.equals("")){
                return uploadUrl;
            }else {
                System.err.println("文件上传至阿里云出现问题 uploadAiOSS()");
                return "";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String upload(MultipartFile file) throws Exception {
        String uploadUrlPath = "";
        if (file == null){
            throw new RuntimeException("文件为空");
        }
        String filename = file.getOriginalFilename();
        if (filename.trim().equals("")){
            throw new RuntimeException("文件名空");
        }
        uploadUrlPath = uploadAiOSS(file);
        if (uploadUrlPath.equals("")){
            throw new RuntimeException("上传失败");
        }
        return uploadUrlPath;
    }

}
