package com.lph.fastdfs.datafileprocess;

import com.lph.fastdfs.datafileprocess.util.WebUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebUtilTest {

    @Test
    public void fileUpload() throws IOException {
        Map<String, String> map = new HashMap<>(); // 封装普通参数
        map.put("name", "tom");

        Map<String, File> multipartFileMap = new HashMap<>();// 封装图片文件
        File file = new File("D:\\photos\\1.jpg");
        multipartFileMap.put("image", file);

        String str = WebUtil.doPost("http://192.168.5.117:9999/lph/upload", map, multipartFileMap, 20000, 20000);
        System.err.println(str);
    }
}
