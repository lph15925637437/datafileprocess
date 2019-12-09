package com.lph.fastdfs.datafileprocess.util;


import com.lph.fastdfs.datafileprocess.constant.ApplicationConst;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 数据响应工具类
 * @author: lph
 * @date:  2019/12/5 17:27
 * @version V1.0
 */
public class ResponseUtil {

    /**
     * 文件下载
     * @param response
     * @throws IOException
     */
    public static void out(String filename, byte[] bytes, HttpServletResponse response) throws IOException {
        response.reset();
        response.setContentType("applicatoin/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, ApplicationConst.DEFAULT_CHARSET));
        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.close();
    }
}
