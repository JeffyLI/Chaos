package com.jeffy.demo.service;

import javax.servlet.http.HttpServletRequest;

public interface FileService {
    /**
     * 上传文件
     */
    String uploadFile(HttpServletRequest request);
}
