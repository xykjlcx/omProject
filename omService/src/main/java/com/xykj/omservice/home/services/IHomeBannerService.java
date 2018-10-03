package com.xykj.omservice.home.services;

import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.home.po.THomeBannerPo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IHomeBannerService {


    /**
     * 获取所有轮播数据(取课程)
     * @return
     */
    List<TCoursePo> findAll() throws Exception;

    /**
     * 根据id查询
     * @param bannerId
     * @return
     */
    THomeBannerPo findById(int bannerId) throws Exception;


}
