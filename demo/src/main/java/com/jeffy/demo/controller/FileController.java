package com.jeffy.demo.controller;

import com.jeffy.demo.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class FileController extends BaseController{
    @Autowired
    private FileService fileService;

    @RequestMapping(value="/file/fileupload")
    public Map<String,Object> fileupload(HttpServletRequest request, HttpSession session){
        fileService.uploadFile(request);
        Map<String,Object> map=new HashMap<>();
        map.put("msg","success");
        return map;
    }

}
