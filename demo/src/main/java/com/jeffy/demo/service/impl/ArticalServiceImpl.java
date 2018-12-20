package com.jeffy.demo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jeffy.demo.dao.ArticalDao;
import com.jeffy.demo.eneity.Artical;
import com.jeffy.demo.service.ArticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ArticalServiceImpl implements ArticalService {
    @Autowired
    private ArticalDao articalDao;

    @Override
    public List<Artical> selectByMapList(Map<String, Object> params) {
        return articalDao.selectByMap(params).getResult();
    }

    @Override
    public Page<Artical> selectByMapPage(Map<String, Object> params) {
        PageHelper.startPage(Integer.valueOf((String)params.get("pageNum")),
                Integer.valueOf((String)params.get("pageSize")));
        Page<Artical> articalPage=articalDao.selectByMap(params);
        return articalPage;
    }

    @Override
    public void insertData(Map<String,Object> params) {
        Artical artical=new Artical();
        artical.setTitle((String)params.get("title"));
        artical.setLabel((String)params.get("label"));
        artical.setContent((String)params.get("content"));
        artical.setTypeId(Integer.parseInt((String)params.get("typeId")));
        artical.setAuthorId(Integer.parseInt((String)params.get("authorId")));
        artical.setAttachmentPath((String)params.get("attachmentPath"));
        artical.setLikeNum(0);
        artical.setHateNum(0);
        artical.setStatus(1);
        Date date=new Date();
        artical.setCreateTime(date);
        artical.setUpdateTime(date);
        articalDao.insertData(artical);
    }

    @Override
    public void updateByMap(Map<String, Object> params) {
        params.put("updateTime",new Date());
        articalDao.updateByMap(params);
    }

    @Override
    public void deleteByMap(Map<String, Object> params) {
        params.put("status",0);
        params.put("updateTime",new Date());
        articalDao.updateByMap(params);
    }
}
