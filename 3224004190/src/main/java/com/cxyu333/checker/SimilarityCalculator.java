package com.cxyu333.checker;

import java.util.HashSet;
import java.util.Set;

public class SimilarityCalculator {

    /**
     * 计算两个文本重复率
     * @param origText 原文
     * @param copyText 抄袭版
     * @return 重复率，保留两位小数
     */
    public static double calculateSimilarity(String origText, String copyText,int n) {

        //1. N-gram划分
        Set<String> set1 = generateNGramSet(origText, n);
        Set<String> set2 = generateNGramSet(copyText, n);

        //边界情况：切出来集合为空，返回0
        if (set1.isEmpty() && set2.isEmpty()) {
            return 1.0;
        }
        if (set1.isEmpty() || set2.isEmpty()) {
            return 0.0;
        }

        //2. Jaccard 算交并比
//        //2.1 计算交集大小
//        Set<String> intersection = new HashSet<>(set1);
//        intersection.retainAll(set2);
//        //2.2 计算并集大小
//        Set<String> union = new HashSet<>(set1);
//        union.addAll(set2);
//        //2.3 返回交并比
//        return (double) intersection.size() / union.size();

        //优化后代码，遍历较小集合计算交集，避免拷贝整个集合
        Set<String> smaller = set1.size() <= set2.size() ? set1 : set2;
        Set<String> larger = set1.size() > set2.size() ? set1 : set2;

        int intersectionCount = 0;
        for (String ngram : smaller) {
            if (larger.contains(ngram)) {
                intersectionCount++;
            }
        }

        int unionCount = set1.size() + set2.size() - intersectionCount;

        return (double) intersectionCount / unionCount;
    }

    /**
     * N-gram划分方法，将文本按每n个字符滑动切分，返回Set集合
     */
    private static Set<String> generateNGramSet(String text, int n) {
        Set<String> ngramSet = new HashSet<>();

//        //去除空白字符（空格、换行等）
//        String cleanText = text.replaceAll("\\s+", "");
//        //滑动窗口切分
//        for (int i = 0; i <= cleanText.length() - n; i++){
//            String ngram = cleanText.substring(i, i + n);
//            ngramSet.add(ngram);
//        }



        // 用String Builder替代正则表达式去除空白字符
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!Character.isWhitespace(c)) {
                sb.append(c);
            }
        }

        int len = sb.length();
        for (int i = 0; i <= len - n; i++) {
            ngramSet.add(sb.substring(i, i + n));
        }
        return ngramSet;
    }


}

