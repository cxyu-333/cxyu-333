package com.cxyu333.checker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author cxyu
 */
public class FileUtil {

    /**
     * 读取文件内容
     */
    public static String readFile(String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }

    /**
     * 将重复率写入文件，保留两位小数
     */
    public static void writeAnswer(String filePath, double similarity) throws IOException {
        String result = String.format("%.2f", similarity);
        Files.write(Paths.get(filePath), result.getBytes());
    }
}

