package com.jeffy.demo.service;

import com.github.pagehelper.Page;
import com.jeffy.demo.eneity.Artical;

import java.util.List;
import java.util.Map;

public interface ArticalService {
    /**
     * 根据相应条件选择文章(List)
     */
    List<Artical> selectByMapList(Map<String,Object> params);

    /**
     * 根据相应条件选择文章(Page)
     */
    Page<Artical> selectByMapPage(Map<String,Object> params);

    /**
     * 插入文章数据
     */
    void insertData(Map<String,Object> params);

    /**
     * 根据条件更新文章数据
     */
    void updateByMap(Map<String,Object> params);

    /**
     * 逻辑删除文章数据
     */
    void deleteByMap(Map<String,Object> params);
}
