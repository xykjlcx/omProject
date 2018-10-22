package com.xykj.omadmin.business;

import com.xykj.omadmin.vo.CourseSectionVo;
import org.springframework.stereotype.Service;

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

    void addNewChapterAndSection(CourseSectionVo sectionVo) throws RuntimeException;

    void editChapterAndSection(CourseSectionVo courseSectionVo) throws RuntimeException;

}
