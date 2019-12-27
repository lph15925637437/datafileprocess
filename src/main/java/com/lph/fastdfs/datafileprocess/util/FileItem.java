package com.lph.fastdfs.datafileprocess.util;

import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 封装文件上传信息对象
 */
public class FileItem {

    // 文件名
    private String name;
    // 文件mime_type
    private String contentType;
    // 上传的文件
    private File file;
    private boolean picType;
    private static Pattern pattern = Pattern.compile("(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$");

    public FileItem(File file) {
        this.file = file;
    }

    public boolean isPicType() {
        boolean flag = true;
        String name = file.getName();
        String suffix = name.substring(name.lastIndexOf("."));
        Matcher matcher = pattern.matcher(suffix.toLowerCase());
        if (!matcher.find()) {
            flag = false;
        }
        return flag;
    }

    public String getName() {
        return file.getName();
    }

    public String getContentType() {
        String path = file.getPath();
        return getContentType(path);
    }

    private String getContentType(String path) {
        Path tmpPath = Paths.get(path);
        String contentType = "application/octet-stream";
        try {
            contentType = Files.probeContentType(tmpPath);
            if (StringUtils.isEmpty(contentType)) {
                contentType = "application/octet-stream";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentType;
    }

    public File getFile() {
        return file;
    }

    public byte[] get() {
        BufferedInputStream bis = null;
        ByteArrayOutputStream output = null;
        byte[] contentfile = null;
        // 指定文件的路径
        try {
            // 创建文件读入流对象
            FileInputStream fis = new FileInputStream(file);
            // 创建缓冲流对象
            bis = new BufferedInputStream(fis);
            output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = bis.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            contentfile = output.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return (contentfile != null ? contentfile : new byte[0]);
    }
}
