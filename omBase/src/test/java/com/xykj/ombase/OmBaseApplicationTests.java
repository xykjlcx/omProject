package com.xykj.ombase;

import com.xykj.ombase.utils.OceanDateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OmBaseApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(OceanDateUtil.converDate(System.currentTimeMillis()));
    }



}
