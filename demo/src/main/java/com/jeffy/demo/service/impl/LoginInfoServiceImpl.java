package com.jeffy.demo.service.impl;

import com.jeffy.demo.Util.AESUtil;
import com.jeffy.demo.Util.HmacSHA1;
import com.jeffy.demo.Util.StaticString;
import com.jeffy.demo.dao.LoginInfoDao;
import com.jeffy.demo.eneity.LoginInfo;
import com.jeffy.demo.service.LoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginInfoServiceImpl implements LoginInfoService {
    @Autowired
    private LoginInfoDao loginInfoDao;

    @Override
    public LoginInfo checkLoginIn(Map<String, Object> params) {
        Map<String,Object> loginInfosMap=new HashMap<>();
        //判断账号合法性
        if(params.get("account")==null||!((String)params.get("account")).contains("@")){
            return null;
        }

        loginInfosMap.put("eqAccount",params.get("account"));
        List<LoginInfo> loginInfos=searchByMap(loginInfosMap);
        if(loginInfos.isEmpty()){
            return null;
        }


        LoginInfo loginInfo=loginInfos.get(0);
        String userKey=loginInfo.getAccount().split("@")[0];
        String password=AESUtil.decrypt(loginInfo.getPassword(),StaticString.AESKEYHEAD+userKey);
        password=(new HmacSHA1()).MD5(password+params.get("code"));
        if(!password.equals(params.get("password"))) {
            return null;
        }
        return loginInfo;
    }

    @Override
    public String register(Map<String, Object> params) {
        //判断是否已经注册了
        Map<String,Object> loginInfosMap=new HashMap<>();
        if(params.get("account")==null){
            return "账号不能为空";
        }
        loginInfosMap.put("eqAccount",params.get("account"));
        List<LoginInfo> loginInfos=searchByMap(loginInfosMap);
        if(!loginInfos.isEmpty()){
            LoginInfo loginInfo=loginInfos.get(0);
            if(loginInfo.getStatus().equals("2")){
                return "该账号已经注册！";
            }else if(loginInfo.getStatus().equals("1")){
                return "账号未激活，请查收激活邮件激活！";
            }else{
                return "账号错误！";
            }
        }
        //账号合法，插入数据
        LoginInfo insertData=new LoginInfo();
        insertData.setStatus("2");  //1：未激活，2：已激活
        insertData.setChances(5);
        insertData.setAccount((String)params.get("account"));
        String userKey=insertData.getAccount().split("@")[0];
        String password= AESUtil.encrypt((String)params.get("password"),StaticString.AESKEYHEAD+userKey);
        if(password==null){
            return "注册失败，密码不符合要求。";
        }

        insertData.setPassword(password);
        Date date=new Date();
        insertData.setCreateTime(date);
        insertData.setUpdateTime(date);
        loginInfoDao.insert(insertData);
        //发送email，激活账号
        return "注册成功！";
    }

    @Override
    public List<LoginInfo> searchByMap(Map<String, Object> params) {
        return loginInfoDao.searchByMap(params);
    }


}
