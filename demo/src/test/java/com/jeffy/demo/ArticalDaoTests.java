package com.jeffy.demo;

import com.jeffy.demo.dao.ArticalDao;
import com.jeffy.demo.eneity.Artical;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticalDaoTests {
    @Autowired
    private ArticalDao articalDao;

    @Test
    public void articalDaoTest(){
        /*
        Artical artical=new Artical();
        artical.setTitle("测试一下4");
        artical.setContentPath("/data/file/4.txt");
        artical.setAttachmentPath("/data/attachment/4.rar");
        artical.setAuthorId(1);
        artical.setHateNum(2);
        artical.setLikeNum(2);
        Date date=new Date();
        artical.setCreateTime(date);
        artical.setUpdateTime(date);
        artical.setStatus(1);
        artical.setTypeId(2);
        articalDao.insertData(artical);
        */
        Map<String,Object> params=new HashMap<>();
        params.put("eqAuthorId",1);
        List<Artical> articals=articalDao.selectByMap(params).getResult();
        for(Artical artical:articals){
            System.out.println(artical);
        }
        /*
        params.put("status",2);
        articalDao.updateByMap(params);
        params.clear();
        params.put("status",2);
        System.out.println(articalDao.selectByMap(params).getResult().get(0));
        */
    }
}
