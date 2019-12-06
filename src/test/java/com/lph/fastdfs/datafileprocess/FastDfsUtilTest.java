package com.lph.fastdfs.datafileprocess;

import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastDfsUtilTest {

    public static final Logger logger = LoggerFactory.getLogger(FastDfsUtilTest.class);

    @Autowired
    protected FastFileStorageClient fastFileStorageClient;

    @Autowired
    protected FdfsWebServer fdfsWebServer;

    @Test
    public void testFileDfs() throws FileNotFoundException {
        File file = new File("D:\\image\\12.jpg");
        FileInputStream inputStream = new FileInputStream(file);
        StorePath path = fastFileStorageClient.uploadFile(inputStream, file.length(), FilenameUtils.getExtension(file.getName()), null);
        if (!Objects.nonNull(path)) {
            logger.info("fileUrl == >>文件路径为空...");
        }
        logger.info("文件路径：" + path.getFullPath());
    }

    @Test
    public void getWebUrl() {
        String webServerUrl = fdfsWebServer.getWebServerUrl();
        logger.info(webServerUrl);
    }
}
