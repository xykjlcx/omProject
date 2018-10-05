package com.xykj.omservice.user.dao;

import com.xykj.omservice.bases.BaseJpaDao;
import com.xykj.omservice.user.po.TUserCourseCollectPo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseCollectDao extends BaseJpaDao<TUserCourseCollectPo,Integer> {

    /**
     * 根据用户id查询
     * 倒序
     * @param userId
     * @return
     */
    List<TUserCourseCollectPo> findAllByUserIdOrderByCollectTimeDesc(int userId);

    /**
     * 根据课程id查询
     * 倒序
     * @param courseId
     * @return
     */
    List<TUserCourseCollectPo> findAllByCourseIdOrderByCollectTimeDesc(int courseId);

    /**
     * 根据用户id和课程id查询
     * @param userId
     * @param courseId
     * @return
     */
    List<TUserCourseCollectPo> findAllByUserIdAndCourseId(int userId,int courseId);

}
