package com.xykj.omadmin;

import com.xykj.omadmin.utils.AliOssStorage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OmAdminApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testOss(){
        AliOssStorage.getFiltList();
    }

    @Test
    public void testFileSave(){
        String path = "/Users/ocean/Documents/tests";
        File file = new File(path);
        try {
            File filearr[] = file.listFiles();
            for(int i=0;i<filearr.length;i++){
                System.out.println(filearr[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
