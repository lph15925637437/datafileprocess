package com.lph.fastdfs.datafileprocess.controller;

import com.lph.fastdfs.datafileprocess.constant.ApplicationConst;
import com.lph.fastdfs.datafileprocess.util.Base64;
import com.lph.fastdfs.datafileprocess.util.FastDfsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
@RequestMapping("/dfs")
public class FastDfsController {

    public static final Logger logger = LoggerFactory.getLogger(FastDfsController.class);

    @Autowired
    private FastDfsUtil fastDfsUtil;

    @RequestMapping("/upload")
    public String upload(String path) throws IOException {
        String accessUrl = fastDfsUtil.upload(new File(path));
        logger.info("upload file res access url:{}", accessUrl);
        return "success";
    }

    @RequestMapping("/download")
    public void download(String path, HttpServletResponse response) throws IOException {
        String decode = URLDecoder.decode(Base64.decodeData(path), ApplicationConst.DEFAULT_CHARSET);
        fastDfsUtil.download(decode, null, response);
    }

    @RequestMapping("/delete")
    public String delete(String path) throws UnsupportedEncodingException {
        String decode = URLDecoder.decode(Base64.decodeData(path), ApplicationConst.DEFAULT_CHARSET);
        boolean flag = fastDfsUtil.delete(decode);
        if (!flag) {
            return "删除失败";
        }
        return "删除成功";
    }
}