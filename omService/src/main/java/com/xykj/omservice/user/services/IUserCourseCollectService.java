package com.xykj.omservice.user.services;

import com.xykj.omservice.bases.BaseService;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.user.po.TUserCourseCollectPo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserCourseCollectService extends BaseService<TUserCourseCollectPo,Integer> {

    /**
     * 根据用户id查询收藏课程
     * @param userId
     * @return
     */
    List<TCoursePo> queryCollectCourseByUserId(int userId) throws RuntimeException;

    /**
     * 添加收藏课程
     * @param userId
     * @param courseId
     */
    void addCollectCourses(int userId,int courseId) throws RuntimeException;

    /**
     * 判断是否收藏过
     * @param userId
     * @param courseId
     * @throws RuntimeException
     */
    boolean isCollectCourse(int userId,int courseId) throws RuntimeException;

    /**
     * 取消收藏课程
     * @param userId
     * @param courseId
     */
    void deleteCollectCourse(int userId,int courseId)throws RuntimeException;

}
