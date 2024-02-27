package org.example.myojssm.common.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * Description: cmd 命令工具类
 * User: liaoyueyue
 * Date: 2024-01-11
 * Time: 16:56
 */
public class CommandUtil {
    public static void main(String[] args) {
        int testCode = run("javac", "stdOutFile.txt", "stdErrFile.txt");
        System.out.println("testCode = " + testCode);
    }

    /**
     * 执行 cmd 命令
     * @param cmd 需要执行的 cmd 命令
     * @param stdOutFile 执行命令后标准输出文件路径
     * @param stdErrFile 执行命令后标准异常文件路径
     * @return 子进程的状态码
     */
    public static int run(String cmd, String stdOutFile, String stdErrFile) {
        // 1. 通过 Runtime 类得到 runtime 实例，执行 exec 方法
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            if (stdOutFile != null) {
                InputStream stdOutFrom = process.getInputStream();
                FileOutputStream stdOutTo = new FileOutputStream(stdOutFile);
                // 2. 获取到标准输出并写入到指定文件
                while (true) {
                    int ch = stdOutFrom.read();
                    if (ch == -1) {
                        break;
                    }
                    stdOutTo.write(ch);
                }
                stdOutFrom.close();
                stdOutTo.close();
            }
            // 3. 获取到标准异常并写入到指定文件
            if (stdErrFile != null) {
                InputStream stdErrFrom = process.getErrorStream();
                FileOutputStream stdErrTo = new FileOutputStream(stdErrFile);
                // 2. 获取到标准输出并写入到指定文件
                while (true) {
                    int ch = stdErrFrom.read();
                    if (ch == -1) {
                        break;
                    }
                    stdErrTo.write(ch);
                }
                stdErrFrom.close();
                stdErrTo.close();
            }
            // 4. 等待子进程结束，返回子进程的状态码
            int exitCode = process.waitFor();
            return exitCode;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
