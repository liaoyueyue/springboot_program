package org.example.myojssm.common.utils;

import javax.swing.filechooser.FileSystemView;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Description: 文件工具类
 * User: liaoyueyue
 * Date: 2024-01-12
 * Time: 22:50
 */
public class FileUtil {
    public static void main(String[] args) {
        String desktopPath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
        String testFilePath = desktopPath + "/test.txt";
        writerFile(testFilePath, "Hello World");
        System.out.println("readFile(testFilePath) = " + readFile(testFilePath));
    }

    /**
     * 读取指定文件内容转为字符串并返回
     *
     * @param filePath 指定文件路径
     * @return 指定文件内文本
     */
    public static String readFile(String filePath) {
        StringBuilder result = new StringBuilder();
//        try(FileReader reader = new FileReader(filePath)) {
//            while (true) {
//                int ch = reader.read();
//                if (ch == -1) {
//                    break;
//                }
//                result.append((char) ch);
        try (FileInputStream fis = new FileInputStream(filePath);
             InputStreamReader isr = new InputStreamReader(fis, "GBK");
             BufferedReader reader = new BufferedReader(isr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result.toString();
    }

    /**
     * 将指定字符串写入指定文件中
     *
     * @param filePath 指定文件路径
     * @param content  指定字符串
     */
    public static void writerFile(String filePath, String content) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
