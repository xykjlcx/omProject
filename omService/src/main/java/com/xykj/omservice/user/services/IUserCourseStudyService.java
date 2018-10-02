package com.xykj.omservice.user.services;

import com.xykj.omservice.bases.BaseService;
import com.xykj.omservice.user.po.TUserCourseStudyPo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserCourseStudyService extends BaseService<TUserCourseStudyPo,Integer> {

    /**
     * 获取我的课程
     * @param userId
     * @return
     */
    List<TUserCourseStudyPo> getMyStudyCourseByUserId(int userId) throws Exception;


}
