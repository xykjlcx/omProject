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


    /**
     * 判断该id的课程是否在首页轮播中
     * @param courseId
     * @return
     */
    boolean isCourseBelongToBanner(int courseId) throws RuntimeException;

    /**
     * 添加新的课程到轮播
     * @param courseId
     */
    void addCourseToBanner(int courseId) throws RuntimeException;

    /**
     * 从轮播中删除id为courseId的课程
     * @param courseId
     */
    void deleteCourseFromBanner(int courseId) throws RuntimeException;


}
