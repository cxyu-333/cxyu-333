package com.cxyu333.checker;

import org.junit.Test;
import static org.junit.Assert.*;

public class SimilarityCalculatorTest {

    // ========== 正常功能测试 ==========

    /**
     * TC-01: 两文本完全相同，相似度应为 1.0
     */
    @Test
    public void testIdenticalTexts() {
        double result = SimilarityCalculator.calculateSimilarity("你好世界", "你好世界", 2);
        assertEquals(1.0, result, 0.001);
    }

    /**
     * TC-02: 两文本完全不同，相似度应为 0.0
     */
    @Test
    public void testCompletelyDifferentTexts() {
        double result = SimilarityCalculator.calculateSimilarity("你好世界", "他好宇宙", 2);
        assertEquals(0.0, result, 0.001);
    }

    /**
     * TC-03: 两文本部分相同，相似度应在 (0, 1) 之间
     */
    @Test
    public void testPartiallySimilarTexts() {
        double result = SimilarityCalculator.calculateSimilarity("abcdef", "abcxyz", 2);
        assertTrue("相似度应在0到1之间", result > 0.0 && result < 1.0);
    }

    /**
     * TC-04: 单字符文本（长度小于n），集合为空，两空集返回1.0
     */
    @Test
    public void testSingleCharTexts() {
        double result = SimilarityCalculator.calculateSimilarity("a", "b", 2);
        assertEquals(1.0, result, 0.001);
    }

    /**
     * TC-05: 一个为空一个非空，返回 0.0
     */
    @Test
    public void testOneEmptyOneNonEmpty() {
        double result = SimilarityCalculator.calculateSimilarity("", "hello", 2);
        assertEquals(0.0, result, 0.001);
    }

    /**
     * TC-06: 两个都为空字符串，返回 1.0
     */
    @Test
    public void testBothEmptyStrings() {
        double result = SimilarityCalculator.calculateSimilarity("", "", 2);
        assertEquals(1.0, result, 0.001);
    }

    // ========== 空白字符处理测试 ==========

    /**
     * TC-07: 含空格/换行的文本，去除空白后应与无空白文本相同
     */
    @Test
    public void testWhitespaceHandling() {
        double result = SimilarityCalculator.calculateSimilarity("你好 世界", "你好世界", 2);
        assertEquals(1.0, result, 0.001);
    }

    /**
     * TC-08: 含换行符的文本处理
     */
    @Test
    public void testNewlineHandling() {
        double result = SimilarityCalculator.calculateSimilarity("你好\n世界", "你好世界", 2);
        assertEquals(1.0, result, 0.001);
    }

    // ========== 不同 N-gram 粒度测试 ==========

    /**
     * TC-09: 使用 n=3（trigram）计算
     */
    @Test
    public void testTrigram() {
        double result = SimilarityCalculator.calculateSimilarity("abcdefg", "abcdefg", 3);
        assertEquals(1.0, result, 0.001);
    }

    /**
     * TC-10: 使用 n=1（unigram）计算
     */
    @Test
    public void testUnigram() {
        double result = SimilarityCalculator.calculateSimilarity("abc", "abd", 1);
        assertTrue("unigram下abc和abd应有较高相似度", result >= 0.5);
    }

    // ========== 边界情况测试 ==========

    /**
     * TC-11: 文本长度恰好等于 n
     */
    @Test
    public void testTextLengthEqualsN() {
        double result = SimilarityCalculator.calculateSimilarity("ab", "ab", 2);
        assertEquals(1.0, result, 0.001);
    }

    /**
     * TC-12: 仅空白字符的文本
     */
    @Test
    public void testOnlyWhitespace() {
        double result = SimilarityCalculator.calculateSimilarity("   ", "   ", 2);
        assertEquals(1.0, result, 0.001);
    }

    /**
     * TC-13: 英文文本测试
     */
    @Test
    public void testEnglishText() {
        double result = SimilarityCalculator.calculateSimilarity("hello world", "hello world", 2);
        assertEquals(1.0, result, 0.001);
    }

    /**
     * TC-14: 大小写敏感测试（应区分大小写）
     */
    @Test
    public void testCaseSensitive() {
        double result = SimilarityCalculator.calculateSimilarity("Hello", "hello", 2);
        assertNotEquals("大小写应产生不同结果", 1.0, result, 0.001);
    }
}

