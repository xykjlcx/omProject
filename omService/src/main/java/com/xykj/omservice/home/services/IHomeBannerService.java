package com.xykj.omservice.home.services;

import com.xykj.omservice.home.po.THomeBannerPo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IHomeBannerService {


    /**
     * 获取所有轮播
     * @return
     */
    List<THomeBannerPo> findAll();

    /**
     * 根据id查询
     * @param bannerId
     * @return
     */
    THomeBannerPo findById(int bannerId);


}
