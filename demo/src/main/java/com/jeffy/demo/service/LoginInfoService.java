package com.jeffy.demo.service;

import com.jeffy.demo.eneity.LoginInfo;

import java.util.List;
import java.util.Map;

public interface LoginInfoService {
    /**
     * 检验登录合法性
     * @param params
     * account:账号
     * password：密码
     * @return
     */
    public LoginInfo checkLoginIn(Map<String,Object> params);

    /**
     * 注册账号
     * @param params
     * @return
     */
    public String register(Map<String, Object> params);

    /**
     * 根据条件查找账号信息
     * @return
     */
    public List<LoginInfo> searchByMap(Map<String,Object> params);
}
