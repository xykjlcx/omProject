package com.xykj.omapp.business;

import com.xykj.omapp.vo.MyCommentVo;
import com.xykj.omapp.vo.UserCourseVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ocean
 * @Title: IUserCourseBusiness
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/1下午5:36
 */
@Service
public interface IUserCourseBusiness {

    /**
     * 根据用户id返回我的课程数据
     * @param userId
     * @return
     */
    List<UserCourseVo> getMyCoursesByUserId(int userId) throws Exception;

    List<MyCommentVo> getMyCommentsByUserId(int userId) throws Exception;

}
