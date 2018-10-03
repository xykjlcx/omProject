package com.xykj.omservice.home.services.impl;

import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.ombase.utils.error.OceanException;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.home.dao.HomeBannerDao;
import com.xykj.omservice.home.po.THomeBannerPo;
import com.xykj.omservice.home.services.IHomeBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeBannerService implements IHomeBannerService {

    @Autowired
    HomeBannerDao homeBannerDao;
    @Autowired
    CourseDao courseDao;

    @Override
    public List<TCoursePo> findAll() throws OceanException {
        List<TCoursePo> tCoursePoList = new ArrayList<>();
        List<THomeBannerPo> homeBannerPoList = homeBannerDao.findAll();
        if (OceanOperationUtil.isNullOrEmptyForCollection(homeBannerPoList)){
            throw new RuntimeException("暂无轮播数据!");
        }
        homeBannerPoList.forEach(tHomeBannerPo -> {
            tCoursePoList.add(courseDao.findById(tHomeBannerPo.getCourseId()).get());
        });
        return tCoursePoList;
    }

    @Override
    public THomeBannerPo findById(int bannerId) throws OceanException {
        THomeBannerPo homeBannerPo = homeBannerDao.findById(bannerId).get();
        if (homeBannerPo == null){
            throw new OceanException("没有id为" + bannerId + "的轮播数据");
        }
        return homeBannerPo;
    }

}
