package com.xykj.omservice;

import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.course.dao.CourseClassifyDao;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.course.po.TCourseClassifyPo;
import com.xykj.omservice.course.po.TCoursePo;
import com.xykj.omservice.course.services.impl.CourseServiceImpl;
import com.xykj.omservice.home.dao.NoticesDao;
import com.xykj.omservice.user.dao.RoleDao;
import com.xykj.omservice.user.dao.UserCourseStudyDao;
import com.xykj.omservice.user.dao.UserDao;
import com.xykj.omservice.user.po.TUserCourseStudyPo;
import com.xykj.omservice.user.po.TUserPo;
import com.xykj.omservice.user.services.impl.UserCourseStudyServiceImpl;
import com.xykj.omservice.user.services.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OmServiceApplicationTests {

//    @Autowired
//    NoticesDao noticesDao;
//
//    @Autowired
//    RoleDao roleDao;
//
//    @Test
//    public void contextLoads() {
//        System.out.println(noticesDao.findAll());
//    }
//
//    @Autowired
//    UserCourseStudyDao studyDao;
//
//    @Autowired
//    CourseDao courseDao;
//
//    @Autowired
//    UserCourseStudyServiceImpl userCourseStudyService;
//
//    @Test
//    public void testDestince(){
////        List<Object[]> list = studyDao.findTUserCourseStudyPosByUserId(1);
////        List<Map<String,Object>> data = new ArrayList<>();
////        list.forEach(objects -> {
////            Object[] row = objects;
////            Map<String,Object> rowMap = new HashMap<>();
////            rowMap.put("courseId",row[0]);
////            rowMap.put("lastStudyTime",row[1]);
////            rowMap.put("userCourseStudy",studyDao.findFirstByUserIdAndCourseIdOrderByLastStudyTimeDesc(1, (Integer) row[0]));
////            data.add(rowMap);
////        });
//        try {
//            System.out.println(userCourseStudyService.getMyStudyCourseByUserId(1));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // 测试结果如下 SUCCESS
//        /**
//         * [{lastStudyTime=2018-10-01 14:23:45.0, userCourseStudy=TUserCourseStudyPo(id=8, userId=1, courseId=3, sectionId=3, firstStudyTime=2018-10-01 14:23:41.0, lastStudyTime=2018-10-01 14:23:45.0, createTime=2018-10-01 14:23:47.0, status=0), courseId=3}, {lastStudyTime=2018-10-01 14:22:18.0, userCourseStudy=TUserCourseStudyPo(id=7, userId=1, courseId=2, sectionId=2, firstStudyTime=2018-10-01 14:22:13.0, lastStudyTime=2018-10-01 14:22:18.0, createTime=2018-10-01 14:22:21.0, status=0), courseId=2}, {lastStudyTime=2018-10-01 11:14:32.0, userCourseStudy=TUserCourseStudyPo(id=5, userId=1, courseId=1, sectionId=4, firstStudyTime=2018-10-01 11:14:28.0, lastStudyTime=2018-10-01 11:14:32.0, createTime=2018-10-01 11:14:36.0, status=0), courseId=1}]
//         */
//    }
//
//    @Test
//    public void testClassify(){
//        Pageable pageable = new PageRequest(2,2);
//        List<TCoursePo> tCoursePos = courseDao.findAllByClassifyId(13,pageable);
//        System.out.println(tCoursePos);
//    }
//
//    @Autowired
//    CourseClassifyDao courseClassifyDao;
//
//    @Test
//    public void test20(){
//        List<TCourseClassifyPo> tCourseClassifyPoList = courseClassifyDao.findAllByParentIdOrderBySequenceAsc(0);
//        System.out.println(tCourseClassifyPoList);
//    }
//
//    @Test
//    public void testjas(){
//        Pageable pageable = new PageRequest(1,2);
//        List<TCoursePo> list = courseDao.findAllByClassifyId(3,pageable);
//        System.out.println(list);
//    }
//
//    @Test
//    public void testmd5(){
////        String account = "xykjlcx";
////        String pwd = "zy1314521..";
////        String encrypt = OceanOperationUtil.md5(OceanOperationUtil.md5(account) + OceanOperationUtil.md5(pwd));
////        System.out.println(encrypt);
//        System.out.println(OceanOperationUtil.md5("zy1314521.."));
//    }
//
//    @Autowired
//    UserServiceImpl userService;
//
//    @Test
//    public void testUserRegister(){
//        TUserPo tUserPo = new TUserPo();
//        tUserPo.setUserName("newAccount");
//        tUserPo.setPassword(OceanOperationUtil.md5("jass"));
//        tUserPo.setEmail("313143431!qq.com");
//        userService.register(tUserPo);
//    }
//
//    @Test
//    public void testLogin(){
//        try {
//            System.out.println(userService.login("newAccount",OceanOperationUtil.md5("jass")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Autowired
//    UserDao userDao;
//
//    @Test
//    public void testUpdateUser(){
//       try {
//           TUserPo userPo = new TUserPo();
//           userPo.setId(1);
//           userPo.setUserName("jass");
//           userService.updateInfo(userPo);
//       }catch (Exception e){
//           e.printStackTrace();
//       }
//    }
//
//    @Test
//    public void testMd5123(){
//        System.out.println(OceanOperationUtil.md5("123456"));
//    }
//
//    @Autowired
//    CourseServiceImpl courseService;
//
//    @Test
//    public void testSearch(){
//        Pageable pageable = new PageRequest(1,1);
//        List<TCoursePo> coursePoList = courseService.searchCourseByNameForPage("s",pageable);
//        System.out.println(coursePoList);
//    }
//
//    @Autowired
//    CourseDao courseDao;
//
//    @Test
//    public void testCount(){
//        System.out.println(courseDao.count());
//    }

}
