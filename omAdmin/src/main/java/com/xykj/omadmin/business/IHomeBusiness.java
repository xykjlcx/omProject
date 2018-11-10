package com.xykj.omadmin.business;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ocean
 * @Title: IHomeBusiness
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/11/1011:37 AM
 */
@Service
public interface IHomeBusiness {

    /**
     * 获取首页数据
     * @return
     */
    Map<String,Object> getHomeData() throws RuntimeException;

}
