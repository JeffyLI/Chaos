package com.jeffy.demo.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    public Map<String,Object> getRequestMap(HttpServletRequest request){
        Map<String,Object> reMap=new HashMap<>();
        Enumeration<String> keyNames=request.getParameterNames();
        while(keyNames.hasMoreElements()){
            String keyName=keyNames.nextElement();
            if(!("").equals(request.getParameter(keyName))) {
                reMap.put(keyName, myFilter(request.getParameter(keyName)));
            }
        }
        return reMap;
    }

    private String myFilter(String content){
        String output=content;

        output=output.replace("alert","");

        return output;
    }
}
