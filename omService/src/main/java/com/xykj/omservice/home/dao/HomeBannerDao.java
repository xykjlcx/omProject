package com.xykj.omservice.home.dao;

import com.xykj.omservice.bases.BaseJpaDao;
import com.xykj.omservice.home.po.THomeBannerPo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeBannerDao extends BaseJpaDao<THomeBannerPo,Integer> {

    /**
     * 根据课程id查询
     * @param courseId
     * @return
     */
    List<THomeBannerPo> findAllByCourseId(int courseId);

    /**
     * 根据课程id删除轮播
     * @param courseId
     */
    void deleteByCourseId(int courseId);

}
