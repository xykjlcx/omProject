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
    List<TCourseClassifyPo> findAnyLevel(int parentId) throws Exception;

    /**
     * 根据分类id查询
     * @param id
     * @return
     */
    TCourseClassifyPo findById(int id);

    /**
     * 添加新的分类
     * @param classifyName
     * @param parentId
     */
    void addNewClassify(String classifyName,Integer parentId);

    /**
     * 编辑已有分类
     * @param selfId
     * @param classifyName
     * @param parentId
     */
    void editClassifyInfo(Integer selfId,String classifyName,Integer parentId);

    /**
     * 根据id删除分类，并删除以此id为父分类的所有子分类
     * @param delId
     */
    void deleteClassifyAndAllChildClassify(int delId) throws RuntimeException;

}
