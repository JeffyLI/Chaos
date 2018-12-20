package com.jeffy.demo;

import com.jeffy.demo.dao.LoginInfoDao;
import com.jeffy.demo.eneity.LoginInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTests {

    @Autowired
    private LoginInfoDao loginInfoDao;

    @Test
    public void LoginInfoDaoTests() {
        System.out.println("----------Test Start(LoginInfoDao)----------");
        //插入
        /*
        LoginInfo loginInfo=new LoginInfo();
        loginInfo.setAccount("LoginInfoDaoTests@qq.com");
        loginInfo.setPassword("Test1");
        loginInfo.setStatus("1");
        loginInfo.setUpdateTime(new Date());
        loginInfo.setChances(5);
        loginInfoDao.insert(loginInfo);
        System.out.println("Insert:"+loginInfo.toString());
        */

        Map<String,Object> params=new HashMap<>();
        LoginInfo loginInfo1;
        params.put("eqAccount","LoginInfoDaoTests@qq.com");

        //查询

        Assert.assertEquals("查询数量验证:",1,loginInfoDao.searchByMap(params).size());
        loginInfo1 =loginInfoDao.searchByMap(params).get(0);
        Assert.assertEquals("账号字段验证:","LoginInfoDaoTests@qq.com",loginInfo1.getAccount());
        Assert.assertEquals("密码字段验证:","Test1",loginInfo1.getPassword());
        Assert.assertEquals("状态字段验证:","1",loginInfo1.getStatus());


        //更新
        /*
        params.put("account","LoginInfoDaoTestsTemp@qq.com");
        params.put("status","2");
        params.put("password","Test2");
        loginInfoDao.update(params);
        System.out.println("Update:"+params.toString());

        params.put("eqAccount","LoginInfoDaoTestsTemp@qq.com");
        Assert.assertEquals("查询数量验证:",1,loginInfoDao.searchByMap(params).size());
        loginInfo1 =loginInfoDao.searchByMap(params).get(0);
        Assert.assertEquals("账号字段验证:","LoginInfoDaoTestsTemp@qq.com",loginInfo1.getAccount());
        Assert.assertEquals("密码字段验证:","Test2",loginInfo1.getPassword());
        Assert.assertEquals("状态字段验证:","2",loginInfo1.getStatus());
        */

        //删除
        /*
        loginInfoDao.deleteByMap(params);
        Assert.assertSame("查询数量验证",0,loginInfoDao.searchByMap(params).size());
        */

        System.out.println("----------Test End(LoginInfoDao)----------");
    }
}
