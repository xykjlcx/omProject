package com.xykj.omservice.course.services.impl;

import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.ombase.utils.error.OceanException;
import com.xykj.omservice.course.dao.CourseClassifyDao;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.course.po.TCourseClassifyPo;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.services.ICourService;
import org.omg.CORBA.TCKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourService {


    @Autowired
    CourseDao courseDao;

    @Autowired
    CourseClassifyDao courseClassifyDao;


    @Override
    public List<TCoursePo> findAllByPage(Pageable pageable) throws Exception {
        Page<TCoursePo> poPage = courseDao.findAll(pageable);
        List<TCoursePo> coursePoList = poPage.getContent();
        if (OceanOperationUtil.isNullOrEmptyForCollection(coursePoList)){
            throw new OceanException("findAllByWeightPage() 获取所有课程列表为空");
        }
        return coursePoList;
    }

    @Override
    public Page<TCoursePo> findByClassifyIdAndPage(int classifyId, Pageable pageable) throws Exception {
        Page<TCoursePo> coursePoListPage = null;
        if (classifyId == 0){
            coursePoListPage = courseDao.findAll(pageable);
        }else {
            TCourseClassifyPo courseClassifyPo = courseClassifyDao.findById(classifyId).get();
            if (courseClassifyPo == null){
                throw new OceanException("根据分类查询课程失败，分类不存在");
            }
            coursePoListPage = courseDao.findAllByClassifyId(classifyId,pageable);
        }
        if (OceanOperationUtil.isNullOrEmptyForCollection(coursePoListPage.getContent())){
            throw new RuntimeException("没有查询到该分类下的课程");
        }
        return coursePoListPage;
    }

    @Override
    public List<TCoursePo> searchCourseByNameForPage(String courseName, Pageable pageable) throws RuntimeException {
        List<TCoursePo> resultCourseList = courseDao.findAllByCourseNameLike("%" + courseName + "%",pageable);
        if (OceanOperationUtil.isNullOrEmptyForCollection(resultCourseList)){
            throw new RuntimeException("没有搜索到课程");
        }
        return resultCourseList;
    }

    @Override
    public void addNewCourse(TCoursePo tCoursePo) throws RuntimeException {
        if (tCoursePo == null){
            throw new RuntimeException("传参有误");
        }
        tCoursePo.setCreateCourseTime(new Timestamp(System.currentTimeMillis()));
        tCoursePo.setUpdateCourseTime(new Timestamp(System.currentTimeMillis()));
        courseDao.save(tCoursePo);
    }

    @Override
    public void editCourse(TCoursePo tCoursePo) throws RuntimeException {
        if (tCoursePo == null){
            throw new RuntimeException("传参有误");
        }
        tCoursePo.setUpdateCourseTime(new Timestamp(System.currentTimeMillis()));
        courseDao.saveAndFlush(tCoursePo);
    }

    @Override
    public Long getCourseCount() {
        return courseDao.count();
    }

    @Override
    public void editCourseIsPutAway(int courseId,boolean isPutAway) throws RuntimeException {
        List<TCoursePo> checkCourseList = courseDao.findAllById(courseId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkCourseList)){
            throw new RuntimeException("课程不存在");
        }
        TCoursePo updateCoursePo = checkCourseList.get(0);
        if (isPutAway){
            // 上架
            updateCoursePo.setIsPutaway(0);
        }else {
            // 下架
            updateCoursePo.setIsPutaway(1);
        }
        editCourse(updateCoursePo);
    }

    @Override
    public void deleteCourseItem(int courseId) throws RuntimeException {
        List<TCoursePo> checkCourseList = courseDao.findAllById(courseId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkCourseList)){
            throw new RuntimeException("课程不存在");
        }
        TCoursePo coursePo = checkCourseList.get(0);
        courseDao.delete(coursePo);
    }

    @Override
    public TCoursePo getCourseInfoById(int courseId) throws RuntimeException {
        List<TCoursePo> checkCourseList = courseDao.findAllById(courseId);
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkCourseList)){
            throw new RuntimeException("课程不存在");
        }
        return checkCourseList.get(0);
    }
}
