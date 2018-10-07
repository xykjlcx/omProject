package com.xykj.omapp.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ocean
 * @Title: AppUtil
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/4下午2:55
 */
public class AppUtil {

    public static final String ADMIN_EMAIL = "348686686@qq.com";

    public static String getUrlHost(HttpServletRequest request){
        String url = request.getRequestURL().toString();
        String auth = request.getRequestURI();
//        System.out.println("打印host：" + url);
//        System.out.println("打印请求地址：" + auth);
        String[] strings = url.split("/");
//        for (int i = 0; i < strings.length; i++) {
//            System.out.println("拆分url：" + strings[i]);
//        }
        String handleUrl = strings[0] + "//" + strings[2];
        System.out.println("获取请求路径的host：" + handleUrl);
        return handleUrl;
    }

}
