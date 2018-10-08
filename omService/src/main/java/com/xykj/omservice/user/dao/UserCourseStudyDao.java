package com.xykj.omservice.user.dao;

import com.xykj.omservice.bases.BaseJpaDao;
import com.xykj.omservice.user.po.TUserCourseStudyPo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseStudyDao extends BaseJpaDao<TUserCourseStudyPo,Integer> {


    /**
     * 根据用户id获取最近学习课程
     * Object[] 为row数据，自行取得
     * @param userId
     * @return
     */
    @Query(
            "select tuc.courseId,max(tuc.lastStudyTime) as lastStudyTime from TUserCourseStudyPo tuc where tuc.userId = ?1 group by tuc.courseId order by lastStudyTime desc "
    )
    List<Object[]> findTUserCourseStudyPosByUserId(int userId);


    /**
     * 根据用户id和课程id获取最近一次学习的记录
     * @param userId
     * @param courseId
     * @return
     */
    TUserCourseStudyPo findFirstByUserIdAndCourseIdOrderByLastStudyTimeDesc(int userId,int courseId);

    /**
     * 根据用户id和课程id和章节id查询
     * @param userId
     * @param courseId
     * @return
     */
    List<TUserCourseStudyPo> findAllByUserIdAndCourseIdAndSectionId(int userId,int courseId,int sectionId);

    /**
     * 根据用户id和课程id查询
     * @param userId
     * @param courseId
     * @return
     */
    List<TUserCourseStudyPo> findAllByUserIdAndCourseId(int userId,int courseId);

    @Query(
            "SELECT DISTINCT tucs.userId FROM TUserCourseStudyPo tucs WHERE tucs.courseId=?1"
    )
    List<Object[]> countAllByCourseId(int courseId);

}
