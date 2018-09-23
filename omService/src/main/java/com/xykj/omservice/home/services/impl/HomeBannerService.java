package com.xykj.omservice.home.services.impl;

import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.home.dao.HomeBannerDao;
import com.xykj.omservice.home.po.THomeBannerPo;
import com.xykj.omservice.home.services.IHomeBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeBannerService implements IHomeBannerService {

    @Autowired
    HomeBannerDao homeBannerDao;

    @Override
    public List<THomeBannerPo> findAll() {
        List<THomeBannerPo> homeBannerPoList = homeBannerDao.findAll();
        if (OceanOperationUtil.isNullOrEmptyForCollection(homeBannerPoList)){
            throw new NullPointerException("没有轮播数据");
        }
        return homeBannerPoList;
    }

    @Override
    public THomeBannerPo findById(int bannerId) {
        THomeBannerPo homeBannerPo = homeBannerDao.findById(bannerId).get();
        if (homeBannerPo == null){
            throw new NullPointerException("没有id为" + bannerId + "的轮播数据");
        }
        return homeBannerPo;
    }

}
