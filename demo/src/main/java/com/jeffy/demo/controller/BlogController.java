package com.jeffy.demo.controller;

import com.github.pagehelper.Page;
import com.jeffy.demo.eneity.Artical;
import com.jeffy.demo.eneity.LoginInfo;
import com.jeffy.demo.service.ArticalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class BlogController extends BaseController{
    @Autowired
    private ArticalService articalService;
    private static final Integer CREATERID=1;

    @RequestMapping(value="/blog/home")
    public ModelAndView homeView(){
        ModelAndView modelAndView=new ModelAndView("/blog/home");
        return modelAndView;
    }

    @RequestMapping(value="/blog/test")
    public Map<String,Object> test(HttpServletRequest request){
        Map<String,Object> params=getRequestMap(request);
        return params;
    }

    @RequestMapping(value="/blog/update",method={RequestMethod.POST})
    public String update(HttpServletRequest request,HttpSession session){
        Map<String,Object> params=getRequestMap(request);
        if(((LoginInfo)session.getAttribute("User")).getId()!=CREATERID){
            return "少年，你想干嘛！";
        }

        String str="提交成功！";
        try {
            articalService.updateByMap(params);
        }catch(Exception e){
            log.error(e.toString());
            str="提交失败！";
        }
        return str;
    }


    @RequestMapping(value="/blog/save",method={RequestMethod.POST})
    public String save(HttpServletRequest request,HttpSession session){
        Map<String,Object> params=getRequestMap(request);
        if(((LoginInfo)session.getAttribute("User")).getId()!=CREATERID){
            return "少年，你想干嘛！";
        }
        String str="提交成功！";
        try {
            articalService.insertData(params);
        }catch(Exception e){
            log.error(e.toString());
            str="提交失败！";
        }
        return str;
    }

    @RequestMapping(value="/blog/articalList")
    public Map<String,Object> articalList(HttpServletRequest request){
        Map<String,Object> params=getRequestMap(request);
        params.put("eqStatus",1);
        Page<Artical> articalPage=articalService.selectByMapPage(params);

        params.clear();
        params.put("totalPages",articalPage.getPages());
        params.put("currentPage",articalPage.getPageNum());
        params.put("data",articalPage);
        return params;
    }

    @RequestMapping(value="/blog/artical")
    public Artical artical(HttpServletRequest request){
        Map<String,Object> params=getRequestMap(request);
        List<Artical> articalPage=articalService.selectByMapList(params);
        return articalPage.get(0);
    }

    @RequestMapping(value="/blog/checkAuthority",method={RequestMethod.POST})
    public String checkAuthority(HttpServletRequest request,HttpSession session){
        if(((LoginInfo)session.getAttribute("User")).getId()==CREATERID){
            return "0";
        }else{
            return "1";
        }
    }

    @RequestMapping(value="/blog/delete",method={RequestMethod.POST})
    public  Map<String,Object> delete(HttpServletRequest request, HttpSession session){
        Map<String,Object> map=new HashMap<>();
        if(((LoginInfo)session.getAttribute("User")).getId()!=CREATERID){
            map.put("msg","少年，你想干嘛！");
            return map;
        }

        Map<String,Object> params=getRequestMap(request);
        params.put("status",0);
        String str="删除成功！";
        try {
            articalService.updateByMap(params);
        }catch(Exception e){
            log.error(e.toString());
            str="删除失败！";
        }

        map.put("msg",str);
        return map;
    }
}
