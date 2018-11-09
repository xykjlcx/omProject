package com.xykj.omadmin.business.impl;

import com.xykj.omadmin.business.ICourseBusiness;
import com.xykj.omadmin.utils.PoConvertVo;
import com.xykj.omadmin.utils.VoConvertPo;
import com.xykj.omadmin.vo.CourseClassifyVoAdmin;
import com.xykj.omadmin.vo.CourseCommentVoAdmin;
import com.xykj.omadmin.vo.CourseSectionVo;
import com.xykj.ombase.utils.OceanDateUtil;
import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.course.dao.CourseSectionDao;
import com.xykj.omservice.course.po.TCourseClassifyPo;
import com.xykj.omservice.course.po.TCourseCommentPo;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.po.TCourseSectionPo;
import com.xykj.omservice.course.services.ICourService;
import com.xykj.omservice.course.services.ICourseClassifyService;
import com.xykj.omservice.course.services.ICourseCommentService;
import com.xykj.omservice.course.services.ICourseSectionService;
import com.xykj.omservice.course.services.impl.CourseSectionServiceImpl;
import com.xykj.omservice.course.services.impl.CouseClassifyServiceImpl;
import com.xykj.omservice.user.dao.UserDao;
import com.xykj.omservice.user.po.TUserPo;
import com.xykj.omservice.user.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ocean
 * @Title: CourseBusinessImpl
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/21下午3:47
 */
@Service
public class CourseBusinessImpl implements ICourseBusiness {
    @Autowired
    ICourseSectionService courseSectionService;
    @Autowired
    ICourseClassifyService couseClassifyService;
    @Autowired
    ICourseCommentService courseCommentService;
    @Autowired
    UserDao userDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    CourseSectionDao courseSectionDao;

    @Override
    public Map<String, Object> getChapterAndSection(int courseId) throws RuntimeException {
        Map<String,Object> data = null;
        List<CourseSectionVo> chapterVoList = new ArrayList<>();
        List<List<CourseSectionVo>> sectionVoList = new ArrayList<>();
        try {
            data = courseSectionService.getAllChapterAndSection(courseId);
            List<TCourseSectionPo> chapteList = (List<TCourseSectionPo>) data.get("chapter");
            List<List<TCourseSectionPo>> sectionList = (List<List<TCourseSectionPo>>) data.get("section");
            for (int i = 0; i < chapteList.size(); i++) {
                TCourseSectionPo tCourseSectionPo = chapteList.get(i);
                CourseSectionVo courseSectionVo = PoConvertVo.convert(tCourseSectionPo);
                courseSectionVo.setId(i+1);
                chapterVoList.add(courseSectionVo);
            }
//            sectionList.forEach(tCourseSectionPos -> {
//                List<CourseSectionVo> temp = new ArrayList<>();
//                tCourseSectionPos.forEach(tCourseSectionPo -> {
//                    temp.add(PoConvertVo.convert(tCourseSectionPo));
//                });
//                sectionVoList.add(temp);
//            });
            for (int i = 0; i < sectionList.size(); i++) {
                List<TCourseSectionPo> tempPoList = sectionList.get(i);
                List<CourseSectionVo> tempVoList = new ArrayList<>();
                for (int i1 = 0; i1 < tempPoList.size(); i1++) {
                    TCourseSectionPo tempPo = tempPoList.get(i1);
                    CourseSectionVo temVo = PoConvertVo.convert(tempPo);
                    temVo.setId(i1+1);
                    tempVoList.add(temVo);
                }
                sectionVoList.add(tempVoList);
            }
            data.put("chapter",chapterVoList);
            data.put("section",sectionVoList);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<CourseClassifyVoAdmin> getTreeClassify() {
        List<CourseClassifyVoAdmin> dataList = new ArrayList<>();
        try {
            /*一级分类*/
            List<TCourseClassifyPo> levelOneClassifyList = couseClassifyService.findAnyLevel(0);
            for (int i = 0; i < levelOneClassifyList.size(); i++) {
                TCourseClassifyPo tCourseClassifyPo = levelOneClassifyList.get(i);
                CourseClassifyVoAdmin courseClassifyVoAdmin = PoConvertVo.convert(tCourseClassifyPo,i+1);
                int parentId = tCourseClassifyPo.getId();
                List<TCourseClassifyPo> tempChildList = couseClassifyService.findAnyLevel(parentId);
                List<CourseClassifyVoAdmin> tempVo = new ArrayList<>();
                for (int i1 = 0; i1 < tempChildList.size(); i1++) {
                    tempVo.add(PoConvertVo.convert(tempChildList.get(i1),(i1+1)));
                }
                courseClassifyVoAdmin.setChilds(tempVo);
                dataList.add(courseClassifyVoAdmin);
            }
            return dataList;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("获取分类树失败");
        }
    }

    @Override
    public void addNewChapterAndSection(CourseSectionVo sectionVo) throws RuntimeException {
       try {
           if (sectionVo == null){
               throw new RuntimeException("新增章节不能为空");
           }
           TCourseSectionPo tCourseSectionPo = VoConvertPo.convert(sectionVo);
           courseSectionService.addNewSection(tCourseSectionPo);
       }catch (RuntimeException e){
           e.printStackTrace();
           throw e;
       }
    }

    @Override
    public void editChapterAndSection(CourseSectionVo courseSectionVo) throws RuntimeException {
        try {
            if (courseSectionVo == null){
                throw new RuntimeException("编辑章节信息不完整");
            }
            TCourseSectionPo tCourseSectionPo = VoConvertPo.convert(courseSectionVo);
            tCourseSectionPo.setId(courseSectionVo.getDbId());
            courseSectionService.editSection(tCourseSectionPo);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 获取评论管理模块数据
     * @param pageable
     * @return
     * @throws RuntimeException
     */
    @Override
    public Map<String,Object> getCommentsManagerData(Pageable pageable) throws RuntimeException {
        Map<String,Object> data = new HashMap<>();
        List<CourseCommentVoAdmin> commentVoAdminList = new ArrayList<>();
        Page<TCourseCommentPo> commentsPage = courseCommentService.getCommentsPage(pageable);
        List<TCourseCommentPo> courseCommentPoList = commentsPage.getContent();
        int idNum = 0;
        for (int i = 0; i < courseCommentPoList.size(); i++) {
            TCourseCommentPo tCourseCommentPo = courseCommentPoList.get(i);
            CourseCommentVoAdmin courseCommentVoAdmin = new CourseCommentVoAdmin();
            List<TUserPo> checkUserList = userDao.findAllById(tCourseCommentPo.getUserId());
            // 关联用户信息
            if (OceanOperationUtil.isNullOrEmptyForCollection(checkUserList)){
//                courseCommentPoList.remove(i);
                System.err.println("评论管理数据，查询关联用户信息时出错，不存在该用户，评论id：" + tCourseCommentPo.getId()
                + "，用户id：" +  tCourseCommentPo.getUserId());
//                continue;
                courseCommentVoAdmin.setUserAccount("用户不存在");
                courseCommentVoAdmin.setRealName("用户不存在");
                courseCommentVoAdmin.setUserHeadImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541776662308&di=64d6073b5685bdecc3743139f009b3a6&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3De02b0b502f1f95cab2f89af5a17e154b%2Fd0c8a786c9177f3e1028f2237acf3bc79f3d56fb.jpg");
            }else {
                TUserPo userPoData = checkUserList.get(0);
                courseCommentVoAdmin.setUserAccount(userPoData.getUserName());
                courseCommentVoAdmin.setRealName(userPoData.getRealName());
                courseCommentVoAdmin.setUserHeadImg(userPoData.getHeadImg());
            }
            // 关联课程信息
            List<TCoursePo> checkCoursePoList = courseDao.findAllById(tCourseCommentPo.getCourseId());
            if (OceanOperationUtil.isNullOrEmptyForCollection(checkCoursePoList)){
//                courseCommentPoList.remove(i);
                System.err.println("评论管理数据，查询关联课程信息时出错，不存在该课程，评论id：" + tCourseCommentPo.getId()
                        + "，课程id：" +  tCourseCommentPo.getCourseId());
//                continue;
                courseCommentVoAdmin.setCourseName("课程不存在");
            }else {
                courseCommentVoAdmin.setCourseName(checkCoursePoList.get(0).getCourseName());
            }
            // 关联章节数据
            if (tCourseCommentPo.getSectionId() == -1){
                // 直接对课程进行评论
                courseCommentVoAdmin.setCommentBelongSection("课程简介");
            }else {
                List<TCourseSectionPo> checkSectionPoList = courseSectionDao.findAllById(tCourseCommentPo.getSectionId());
                if (OceanOperationUtil.isNullOrEmptyForCollection(checkSectionPoList)){
//                    courseCommentPoList.remove(i);
                    System.err.println("评论管理数据，查询关联章节信息时出错，不存在该课程，评论id：" + tCourseCommentPo.getId()
                            + "，章节id：" +  tCourseCommentPo.getSectionId());
//                    continue;
                    courseCommentVoAdmin.setCommentBelongSection("章节不存在");
                }else {
                    courseCommentVoAdmin.setCommentBelongSection(checkSectionPoList.get(0).getSectionName());
                }
            }
            // todo 完善评论数据
            courseCommentVoAdmin.setId(pageable.getPageNumber() * pageable.getPageSize() + (++idNum));
            courseCommentVoAdmin.setDbId(tCourseCommentPo.getId());
            courseCommentVoAdmin.setCommentContent(tCourseCommentPo.getCommentContent());
            courseCommentVoAdmin.setCommentTime(OceanDateUtil.converDate(tCourseCommentPo.getCreateTime().getTime()));
            commentVoAdminList.add(courseCommentVoAdmin);
        }
        data.put("comments",commentVoAdminList);
        data.put("count",commentsPage.getTotalElements());
        return data;
    }


}
