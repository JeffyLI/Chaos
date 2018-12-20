package com.jeffy.demo.dao;

import com.jeffy.demo.eneity.LoginInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("loginInfoDao")
public interface LoginInfoDao {
    /**
     * 根据条件查找账号信息
     */
    List<LoginInfo> searchByMap(Map<String,Object> params);

    /**
     *根据条件更新账号信息
     */
    void update(Map<String,Object> params);

    /**
     * 插入账号信息
     * @param loginInfo
     */
    void insert(LoginInfo loginInfo);

    /**
     * 根据条件删除账号信息
     * @param params
     */
    void deleteByMap(Map<String,Object> params);
}
