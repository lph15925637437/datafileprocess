package com.lph.fastdfs.datafileprocess.controller;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("lph")
public class FileUploadController {

    public static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping("/upload")
    public String upload(HttpServletRequest request){

        boolean isMultipart = ServletFileUpload.isMultipartContent(request); // 判断是否是协文件的网络请求
        Map<String, MultipartFile> fileparams=null;
        if(isMultipart){
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
            fileparams = multipartRequest.getFileMap();
            logger.info("MultipartContent：{}", fileparams);
        }

        String parameter = request.getParameter("name");// 获取提交的普通参数
        return parameter;
    }
}
