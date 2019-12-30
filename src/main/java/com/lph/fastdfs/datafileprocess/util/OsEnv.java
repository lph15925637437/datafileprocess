package com.lph.fastdfs.datafileprocess.util;

import java.util.Properties;


/**
 * 获取当前运行的环境工具类
 *
 * @version V1.0
 * @author: lph
 * @date: 2019/12/30 14:49
 */
public class OsEnv {


    public static final String WIN_NEWLINES_SYMBOL = "\\r\\n";
    public static final String LINUX_NEWLINES_SYMBOL = "\\n";

    public static boolean isOsLinux() {
        boolean flag = true;
        Properties properties = System.getProperties();
        String property = properties.getProperty("os.name");
        System.err.println("os.name:" + property);
        if (property != null && property.toLowerCase().contains("linux")) {
            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }

    public static void main(String[] args) {
        boolean osLinux = isOsLinux();
        if (osLinux) {
            System.err.println("当前系统运行在linux环境中");
        } else {
            System.err.println("当前windows环境中");
        }
    }

    public static boolean isEmpty(String value) {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(value.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 注意，在Windows系统中换行是\r\n，在Linux系统中是\n
     *
     */
}
