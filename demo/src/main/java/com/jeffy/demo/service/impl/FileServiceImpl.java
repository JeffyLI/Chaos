package com.jeffy.demo.service.impl;

import com.jeffy.demo.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

import static com.jeffy.demo.Util.StaticString.UPLOAD_FILE_PATH;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(HttpServletRequest request) {
        String msg="success";

        if (ServletFileUpload.isMultipartContent(request)){
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = null;
            Map map =multipartRequest.getFileMap();
            for (Iterator i = map.keySet().iterator(); i.hasNext();) {
                multipartFile=(MultipartFile) map.get(i.next());
            }
            File fileUploadPath = new File(UPLOAD_FILE_PATH);
            if (!fileUploadPath.exists()) {
                fileUploadPath.mkdir();
            }
            try{
                String fileName=multipartFile.getOriginalFilename();
                String saveFileName=UPLOAD_FILE_PATH+fileName;
                System.out.println(saveFileName);
                File saveFile=new File(saveFileName);
                multipartFile.transferTo(saveFile);
                log.info("文件上传成功："+fileName+"--->"+saveFileName);
            }catch(Exception e){
                log.error(e.toString());
                msg="上传失败";
            }
        }

        return msg;
    }
}
