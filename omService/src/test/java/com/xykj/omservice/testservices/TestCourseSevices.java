package com.xykj.omservice.testservices;
import com.xykj.omservice.course.dao.CourseDao;
import com.xykj.omservice.course.dao.CourseSectionDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestCourseSevices {

//    @Autowired
//    CourseDao courseDao;
//
//    @Test
//    public void test1(){
//        Sort sort = new Sort(Sort.Direction.DESC,"weight");
//        Pageable pageable = new PageRequest(0,5,sort);
//        System.out.println(courseDao.findAll(pageable).getContent());
//    }

//    @Autowired
//    CourseSectionDao courseSectionDao;
//
//    @Test
//    public void test1(){
//        System.out.println(courseSectionDao.getMaxsequenceByParentId(0));
//    }


}
