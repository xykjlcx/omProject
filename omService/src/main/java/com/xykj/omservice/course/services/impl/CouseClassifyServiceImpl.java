package com.xykj.omservice.course.services.impl;

import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.course.dao.CourseClassifyDao;
import com.xykj.omservice.course.po.TCourseClassifyPo;
import com.xykj.omservice.course.services.ICourseClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CouseClassifyServiceImpl implements ICourseClassifyService {

    @Autowired
    CourseClassifyDao courseClassifyDao;


    @Override
    public List<TCourseClassifyPo> findAnyLevel(int parentId) throws Exception {
        List<TCourseClassifyPo> courseClassifyPoList;
        if (parentId < 0){
            throw new RuntimeException("该分类不存在");
        }
        courseClassifyPoList = courseClassifyDao.findAllByParentIdOrderBySequenceAsc(parentId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(courseClassifyPoList)){
//            throw new RuntimeException("该分类下不存在子分类");
            System.err.println("该分类下不存在子分类");
            courseClassifyPoList = new ArrayList<>();
        }
        return courseClassifyPoList;
    }

    @Override
    public TCourseClassifyPo findById(int id) {
        TCourseClassifyPo courseClassifyPo = courseClassifyDao.findById(id).get();
        if (courseClassifyPo == null){
            throw new NullPointerException("找不到该分类，分类id为：" + id);
        }
        return courseClassifyPo;
    }

    @Override
    public void addNewClassify(String classifyName, Integer parentId) {
        List<TCourseClassifyPo> checkParentClassifyList = courseClassifyDao.findAllById(parentId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkParentClassifyList) && parentId != 0){
            throw new RuntimeException("操作失败，上级分类不存在");
        }else {
            TCourseClassifyPo newClassifyPo = new TCourseClassifyPo();
            newClassifyPo.setClassifyName(classifyName);
            newClassifyPo.setParentId(parentId);
            Integer beforeMaxSqu = courseClassifyDao.getMaxsequenceByParentId(parentId);
            if (beforeMaxSqu == null){
                newClassifyPo.setSequence(0);
            }else {
                newClassifyPo.setSequence(beforeMaxSqu + 1);
            }
            newClassifyPo.setCreateClassifyTime(new Timestamp(System.currentTimeMillis()));
            newClassifyPo.setUpdateClassifyTime(new Timestamp(System.currentTimeMillis()));
            courseClassifyDao.save(newClassifyPo);
        }
    }

    @Override
    public void editClassifyInfo(Integer selfId, String classifyName, Integer parentId) {
        List<TCourseClassifyPo> checkParentClassifyList = courseClassifyDao.findAllById(parentId);
        List<TCourseClassifyPo> editClassifyPoList = courseClassifyDao.findAllById(selfId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkParentClassifyList) && parentId != 0){
            throw new RuntimeException("操作失败，上级分类不存在");
        }else if (OceanOperationUtil.isNullOrEmptyForCollection(editClassifyPoList)){
            throw new RuntimeException("操作失败，该分类不存在");
        }else {
            // 核心流程：获取目标上级分类的最后序列+1，作为自己所在父分类中的顺序
            TCourseClassifyPo editClassifyPo = editClassifyPoList.get(0);
            if (editClassifyPo.getParentId() == parentId){
                // 分类的上级分类  没有发生改变
            }else {
                editClassifyPo.setParentId(parentId);
                Integer beforeMaxSqu = courseClassifyDao.getMaxsequenceByParentId(parentId);
                if (beforeMaxSqu == null){
                    editClassifyPo.setSequence(0);
                }else {
                    editClassifyPo.setSequence(beforeMaxSqu + 1);
                }
            }
            editClassifyPo.setClassifyName(classifyName);
            editClassifyPo.setUpdateClassifyTime(new Timestamp(System.currentTimeMillis()));
            courseClassifyDao.saveAndFlush(editClassifyPo);
        }
    }


}
