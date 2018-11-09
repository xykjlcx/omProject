package com.xykj.omadmin.business;

import com.xykj.omadmin.vo.CourseClassifyVoAdmin;
import com.xykj.omadmin.vo.CourseCommentVoAdmin;
import com.xykj.omadmin.vo.CourseSectionVo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ocean
 * @Title: ICourseBusiness
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/21下午3:46
 */
@Service
public interface ICourseBusiness {

    Map<String,Object> getChapterAndSection(int courseId) throws RuntimeException;

    List<CourseClassifyVoAdmin> getTreeClassify();

    void addNewChapterAndSection(CourseSectionVo sectionVo) throws RuntimeException;

    void editChapterAndSection(CourseSectionVo courseSectionVo) throws RuntimeException;

    /**
     * 获取评论管理数据
     * @return
     * @throws RuntimeException
     */
    Map<String,Object> getCommentsManagerData(Pageable pageable) throws RuntimeException;

}
