package com.xykj.omapp.business;

import com.xykj.omservice.course.po.TCoursePo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ICourseBusiness {

    /**
     * 获取推荐好课
     * @return
     */
    List<TCoursePo> getRecommendCourse();

    /**
     * 猜你喜欢
     * @return
     */
    List<TCoursePo> getGuessLikeCourse();

    Map<String,Object> getChapterAndSection(int courseId);

}
