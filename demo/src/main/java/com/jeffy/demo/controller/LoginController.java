package com.jeffy.demo.controller;

import com.jeffy.demo.eneity.LoginInfo;
import com.jeffy.demo.service.LoginInfoService;
import com.jeffy.demo.Util.HmacSHA1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class LoginController extends BaseController {
    @Autowired
    private LoginInfoService loginInfoService;

    @RequestMapping(value="/login/login")
    public ModelAndView loginView(HttpSession session){
        ModelAndView mv=new ModelAndView("/login/login");
        String randomstr=(new HmacSHA1()).randomCode(4);
        session.setAttribute("temp",randomstr);
        mv.addObject("code",randomstr);
        return mv;
    }

    @RequestMapping(value="/login/register")
    public ModelAndView registerView(){
        ModelAndView mv=new ModelAndView("/login/register");
        mv.addObject("test","1234");
        return new ModelAndView("/login/register");
    }

    @RequestMapping(value="/index")
    public ModelAndView indexView(){
        return new ModelAndView("/index");
    }

    @RequestMapping(value="/login/registerIn")
    public String registerIn(HttpServletRequest request){
        Map<String,Object> params=getRequestMap(request);
        return loginInfoService.register(params);
    }

    @RequestMapping(value="/login/loginIn")
    public String loginIn(HttpServletRequest request,HttpSession session){
        //检验账号密码的合法性
        Map<String,Object> params=getRequestMap(request);
        params.put("code",session.getAttribute("temp"));
        LoginInfo loginInfo=loginInfoService.checkLoginIn(params);
        if(!(loginInfo==null)){
            if(loginInfo.getStatus().equals("2")) {
                LoginInfo user=new LoginInfo();
                user.setAccount(loginInfo.getAccount());
                user.setId(loginInfo.getId());
                session.setAttribute("User",user);
                return "success";
            }else if(loginInfo.getStatus().equals("1")){
                return "账号未激活，请先登录邮箱激活！";
            }
        }
        return "账号密码不对，请重新输入！";
    }

    @RequestMapping(value="/login/loginOut")
    public ModelAndView loginOut(HttpSession session){
        session.removeAttribute("User");
        return loginView(session);
    }
}
