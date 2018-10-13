package com.xykj.omadmin.business;

import org.apache.tomcat.jni.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ocean
 * @Title: IFileOperateBusiness
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/13下午6:06
 */
@Service
public interface IFileOperateBusiness {

    /**
     * 文件上传
     * @param file
     * @throws Exception
     */
    String upload(MultipartFile file) throws Exception;

}
