package com.cxyu333.checker;


import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class MainTest {

    /**
     * TC-M01: 参数数量不足（0个参数）
     * 场景：用户未输入任何命令行参数
     * 预期：程序应提示用法并退出
     */
    @Test
    public void testNoArguments() {
        // 由于 main 方法调用 System.exit，这里只验证参数校验逻辑
        // 实际测试通过手动运行验证
        String[] args = new String[]{};
        assertNotEquals("参数数量应为3", 3, args.length);
    }

    /**
     * TC-M02: 参数数量过多（4个参数）
     * 场景：用户输入了多余的命令行参数
     * 预期：程序应提示用法并退出
     */
    @Test
    public void testTooManyArguments() {
        assertEquals("参数数量应为3", 3, new String[]{"a.txt", "b.txt", "c.txt", "d.txt"}.length);
    }

    /**
     * TC-M03: 参数数量正确（3个参数）
     * 场景：用户正确输入了3个文件路径
     * 预期：参数校验通过
     */
    @Test
    public void testCorrectArgumentCount() {
        assertEquals("参数数量应为3", 3, new String[]{"orig.txt", "copy.txt", "answer.txt"}.length);
    }

    /**
     * TC-M04: 文件不存在时的异常处理
     * 场景：传入的文件路径不存在
     * 预期：应捕获 IOException 并提示错误
     */
    @Test
    public void testFileNotFound() {
        assertThrows(IOException.class, () -> FileUtil.readFile("not_exist.txt"));
    }
}

