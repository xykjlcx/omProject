package com.xykj.omservice.home.services.impl;

import com.xykj.ombase.utils.OceanDateUtil;
import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.ombase.utils.error.OceanException;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.home.dao.HomeBannerDao;
import com.xykj.omservice.home.po.THomeBannerPo;
import com.xykj.omservice.home.services.IHomeBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    @Override
    public boolean isCourseBelongToBanner(int courseId) {
        List<TCoursePo> checkCourseList = courseDao.findAllById(courseId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkCourseList)){
            throw new RuntimeException("课程不存在");
        }
        List<THomeBannerPo> tHomeBannerPoList = homeBannerDao.findAllByCourseId(courseId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(tHomeBannerPoList)){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void addCourseToBanner(int courseId) {
        List<TCoursePo> checkCourseList = courseDao.findAllById(courseId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkCourseList)){
            throw new RuntimeException("课程不存在");
        }
        List<THomeBannerPo> tHomeBannerPoList = homeBannerDao.findAllByCourseId(courseId);
        if (OceanOperationUtil.isNotNullOrEmptyForCollection(tHomeBannerPoList)){
            throw new RuntimeException("该课程已添加至轮播");
        }
        THomeBannerPo tHomeBannerPo = new THomeBannerPo();
        tHomeBannerPo.setCourseId(courseId);
        tHomeBannerPo.setCreateBannerTime(new Timestamp(OceanDateUtil.getCurrentTime()));
        tHomeBannerPo.setUpdateBannerTime(new Timestamp(OceanDateUtil.getCurrentTime()));
        tHomeBannerPo.setWeight(0);
        homeBannerDao.save(tHomeBannerPo);
    }

    @Override
    public void deleteCourseFromBanner(int courseId) {
        List<TCoursePo> checkCourseList = courseDao.findAllById(courseId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkCourseList)){
            throw new RuntimeException("课程不存在");
        }
        List<THomeBannerPo> tHomeBannerPoList = homeBannerDao.findAllByCourseId(courseId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(tHomeBannerPoList)){
            throw new RuntimeException("该课程不在轮播中");
        }
        homeBannerDao.delete(tHomeBannerPoList.get(0));
    }

}
