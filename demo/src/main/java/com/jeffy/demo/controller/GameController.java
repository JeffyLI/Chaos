package com.jeffy.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
public class GameController extends BaseController{
    @RequestMapping(value="/game/home")
    public ModelAndView homeView(){
        ModelAndView modelAndView=new ModelAndView("/game/home");
        return modelAndView;
    }
}
