package com.xykj.omadmin.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ocean
 * @Title: AliOssStorage
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/13上午11:15
 */
public class AliOssStorage {

    /**
     * endpoint
     */
    private static final String END_POINT = "oss-cn-beijing.aliyuncs.com";
    /**
     * accessKey
     */
    private static final String ACCESS_KEY_ID = "LTAIvfYObgH63CRJ";
    /**
     * accessKeySecret
     */
    private static final String ACCESS_KEY_SECRET = "z0otywyPgMd6ZCkNtdI6zV72K348C1";
    /**
     * bucket名称
     */
    private static final String BUCKET_NAME = "oceanbucket";
    /**
     * 链接前缀
     */
    private static final String URL_PREFIX = "http://oceanbucket.oss-cn-beijing.aliyuncs.com/";

    public static OSSClient getOssClient(){
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        return ossClient;
    }

    public static void getFiltList() {
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        // ossClient.listObjects返回ObjectListing实例，包含此次listObject请求的返回结果。
        ObjectListing objectListing = ossClient.listObjects(BUCKET_NAME);
        // objectListing.getObjectSummaries获取所有文件的描述信息。
        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            System.out.println(" - " + objectSummary.getKey() + "  " +
                    "(size = " + objectSummary.getSize() + ")");
        }
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 上传
     * @param file
     * @return
     */
    public static String upload(File file){
        if(null == file){
            return null;
        }
        System.out.println("==========>OSS文件上传开始");
        OSSClient ossClient = getOssClient();
        try {
            //容器不存在，就创建
            if(! ossClient.doesBucketExist(BUCKET_NAME)){
                ossClient.createBucket(BUCKET_NAME);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(BUCKET_NAME);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            //创建文件路径
            String fileUrl = file.getName();
            //上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(BUCKET_NAME, fileUrl, file));
            //设置权限 这里是公开读
            ossClient.setBucketAcl(BUCKET_NAME,CannedAccessControlList.PublicRead);
            if(null != result){
                System.out.println("==========>OSS文件上传成功,文件名称："+fileUrl);
                // 删除本地文件
                deleteFile(file);
                return URL_PREFIX + fileUrl;
            }
        }catch (OSSException oe){
            System.err.println("oss文件上传失败,OSSException");
            oe.printStackTrace();
        }catch (ClientException ce){
            System.err.println("oss文件上传失败,ClientException");
            ce.printStackTrace();
        }finally {
            //关闭
            ossClient.shutdown();
        }
        return null;
    }

    /**
     * 删除本地文件
     * @param file
     */
    public static void deleteFile(File file){
        if (file != null
                && file.exists()){
            file.delete();
            System.out.println("名称为：" + file.getName() + "的本地文件已删除");
        }
    }



}
