package com.xykj.ombase.utils;

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



}
