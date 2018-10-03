package com.xykj.ombase.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

public class OceanOperationUtil {

    /**
     * 判断集合是否为空
     * 是 返回true
     * 否则 返回false
     * @param collection
     * @return
     */
    public static boolean isNullOrEmptyForCollection(Collection collection){
        if (collection == null
                || collection.isEmpty()){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 判断集合是否非空
     * @param collection
     * @return
     */
    public static boolean isNotNullOrEmptyForCollection(Collection collection){
        if (collection == null
                || collection.isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    public static String md5(String string) {
        if(string == null
                || string.equals("")){
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }



}
