package com.cxyu333.checker;


import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //检查命令行参数
        if (args.length != 3){
            System.out.println("用法：java - jar main.jar <原文文件> <抄袭版文件> <答案文件>");
            System.exit(1);
        }

        String origFile = args[0];
        String copyFile = args[1];
        String answerFile = args[2];

        try {
            //读取文件
            String origText = FileUtil.readFile(origFile);
            String copyText = FileUtil.readFile(copyFile);
            //计算相似度
            double similarity = SimilarityCalculator.calculateSimilarity(origText, copyText, 2);
            //输出结果
            FileUtil.writeAnswer(answerFile, similarity);
        }catch (IOException e){
            System.out.println("文件读写错误：" + e.getMessage());
            System.exit(1);
        }
    }
}

