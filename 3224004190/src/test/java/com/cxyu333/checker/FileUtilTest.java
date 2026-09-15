package com.cxyu333.checker;

import org.junit.After;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.Assert.*;

public class FileUtilTest {

    private static final String TEST_WRITE_PATH = "src/test/test_output_temp.txt";

    /**
     * TC-F01: 正常读取文件
     */
    @Test
    public void testReadFile() throws IOException {
        String content = FileUtil.readFile("src/test/orig.txt");
        assertNotNull("读取内容不应为null", content);
        assertFalse("读取内容不应为空", content.isEmpty());
    }

    /**
     * TC-F02: 正常写入文件
     */
    @Test
    public void testWriteAnswer() throws IOException {
        FileUtil.writeAnswer(TEST_WRITE_PATH, 0.85);
        String content = FileUtil.readFile(TEST_WRITE_PATH);
        assertEquals("0.85", content);
    }

    /**
     * TC-F03: 写入精度测试（保留两位小数）
     */
    @Test
    public void testWriteAnswerPrecision() throws IOException {
        FileUtil.writeAnswer(TEST_WRITE_PATH, 0.123456);
        String content = FileUtil.readFile(TEST_WRITE_PATH);
        assertEquals("0.12", content);
    }

    /**
     * TC-F04: 读取不存在的文件应抛出异常
     */
    @Test(expected = IOException.class)
    public void testReadNonExistentFile() throws IOException {
        FileUtil.readFile("src/test/not_exist_file.txt");
    }

    @After
    public void cleanup() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_WRITE_PATH));
    }
}

