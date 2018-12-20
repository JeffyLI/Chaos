package com.jeffy.demo.dao;

import com.github.pagehelper.Page;
import com.jeffy.demo.eneity.Artical;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("articalDao")
public interface ArticalDao {
    /**
     * 根据相应条件选择文章
     */
    Page<Artical> selectByMap(Map<String,Object> params);
    /**
     *插入文章数据
     */
    void insertData(Artical artical);
    /**
     *更新文章数据
     */
    void updateByMap(Map<String,Object> params);
}
