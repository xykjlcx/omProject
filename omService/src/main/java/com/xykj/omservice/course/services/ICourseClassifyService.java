package com.xykj.omservice.course.services;

import com.xykj.omservice.course.po.TCourseClassifyPo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICourseClassifyService {

    /**
     * 查询任意级分类
     * @param parentId == 0为一级分类
     * @return
     */
    List<TCourseClassifyPo> findAnyLevel(int parentId);

    /**
     * 根据分类id查询
     * @param id
     * @return
     */
    TCourseClassifyPo findById(int id);

}
