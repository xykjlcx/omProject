package com.xykj.omadmin;

import com.xykj.ombase.returnformat.OceanReturn;
import com.xykj.ombase.returnformat.Result;
import com.xykj.omservice.course.dao.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class TestController {

    @Autowired
    CourseDao courseDao;

    @RequestMapping("/test")
    public Result test(){
        Sort sort = new Sort(Sort.Direction.DESC,"weight");
        Pageable pageable = new PageRequest(0,5,sort);
        return OceanReturn.successResult(
                "成功",
                courseDao.findAll(pageable)
        );
    }

}
