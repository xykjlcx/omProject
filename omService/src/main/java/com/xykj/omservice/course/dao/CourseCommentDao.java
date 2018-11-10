package com.xykj.omservice.course.dao;

import com.xykj.omservice.bases.BaseJpaDao;
import com.xykj.omservice.course.po.TCourseCommentPo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ocean
 * @Title: CourseCommentDao
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/3上午10:38
 */
@Repository
public interface CourseCommentDao extends BaseJpaDao<TCourseCommentPo,Integer> {

    List<TCourseCommentPo> findAllByCourseIdOrderByCreateTimeDesc(int courseId, org.springframework.data.domain.Pageable pageable);

    /**
     * 按照用户id查询，并按时间倒序排序
     * @param userId
     * @return
     */
    List<TCourseCommentPo> findAllByUserIdOrderByCreateTimeDesc(int userId);

//    /**
//     * 查询所有，根据评论时间倒序
//     * @return
//     */
//    @Query(
//            "select tccompo from TCourseCommentPo tccompo order by tccompo.createTime desc"
//    )
//    List<TCourseCommentPo> findAllByCreateTimeDesc(Pageable pageable);

    List<TCourseCommentPo> findAllById(int commentId);

    /**
     * 获取最新一条评论数据
     * @return
     */
    @Query(
            value = "SELECT * FROM t_course_comment ORDER BY create_time DESC LIMIT 1;",
            nativeQuery = true
    )
    TCourseCommentPo findRecentlyComment();

}
