package com.cxyu333.checker;

import java.io.IOException;

public class PerformanceTest {
    public static void main(String[] args) throws IOException {
        String baseDir = "3224004190/";
        String origText = FileUtil.readFile(baseDir + "test/orig.txt");
        String copyText = FileUtil.readFile(baseDir + "test/orig_0.8_add.txt");

        int iterations = 10000;

        System.out.println("开始性能测试，循环 " + iterations + " 次...");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < iterations; i++) {
            SimilarityCalculator.calculateSimilarity(origText, copyText, 2);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("总耗时: " + (endTime - startTime) + " ms");
        System.out.println("平均每次: " + (endTime - startTime) * 1.0 / iterations + " ms");
    }
}

