package com.lph.fastdfs.datafileprocess.util;


import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.lph.fastdfs.datafileprocess.constant.ApplicationConst;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

/**
 * 文件操作工具类
 *
 * @version V1.0
 * @author: lph
 * @date: 2019/12/5 17:05
 */
@Component
public class FastDfsUtil {

    public static final Logger logger = LoggerFactory.getLogger(FastDfsUtil.class);

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private FdfsWebServer fdfsWebServer;

    public String upload(MultipartFile file) throws IOException {
        // 设置文件信息
        Set<MetaData> mataData = new HashSet<>();
        mataData.add(new MetaData("author", "fastdfs"));
        mataData.add(new MetaData("description", file.getOriginalFilename()));
        // 上传
        StorePath storePath = fastFileStorageClient.uploadFile(
                file.getInputStream(), file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()),
                null);
        return getResAccessUrl(storePath);
    }

    public String upload(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file.length(), FilenameUtils.getExtension(file.getName()), null);
        return getResAccessUrl(storePath);
    }

    public boolean delete(String path) {
        boolean flag = true;
        try {
            fastFileStorageClient.deleteFile(path);
        } catch (Exception e) {
            logger.error("fast dfs delete fair:{}", e.getMessage());
            flag = false;
        }
        return flag;
    }

    public boolean delete(String path, String group) {
        boolean flag = true;
        try {
            fastFileStorageClient.deleteFile(group, path);
        } catch (Exception e) {
            logger.error("fast dfs delete fair:{}", e.getMessage());
            flag = false;
        }
        return flag;
    }

    public void download(String path, String filename, HttpServletResponse response) throws IOException {
        // 获取文件
        StorePath storePath = StorePath.parseFromUrl(path);
        if (StringUtils.isBlank(filename)) {
            filename = FilenameUtils.getName(storePath.getPath());
        }
        byte[] bytes = fastFileStorageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
        ResponseUtil.out(filename, bytes, response);
    }

    // 封装图片完整URL地址
    private String getResAccessUrl(StorePath storePath) {
        String fileUrl = fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
        return fileUrl;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String path = "http://192.168.177.128:8888/group1/M00/00/00/wKixgF3tru2AeRLTAADY7S0D9f4020.jpg";
        String encode = URLEncoder.encode(path, ApplicationConst.DEFAULT_CHARSET);
        String encodeData = Base64.encodeData(encode);
        logger.info("encode:{}", encode);
        logger.info("encodeData:{}", encodeData);
        String decodeData = Base64.decodeData(encodeData);
        String decode = URLDecoder.decode(decodeData, ApplicationConst.DEFAULT_CHARSET);
        logger.info("decodeData:{}", decodeData);
        logger.info("decode:{}", decode);
    }
}
