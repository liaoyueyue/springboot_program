package org.example.myojssm.entity;


import org.example.myojssm.common.utils.CommandUtil;
import org.example.myojssm.common.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-01-11
 * Time: 17:40
 */
// 编译 + 运行 这个过程 称为 entity.Task 任务
public class Task {
    private String WORD_DIR = null; // 临时文件所在目录
    private String CLASS_NAME = null; // 类名
    private String CODE = null; // 需编译的文件名
    private String COMPILE_ERROR = null; // 编译错误信息文件名
    private String STDOUT = null; // 标准输出的文件名
    private String STDERR = null; // 标准异常的文件名

    public Task() {
        this.WORD_DIR = "./tmp/" + UUID.randomUUID().toString() + "/";
        this.CLASS_NAME = "Solution";
        this.CODE = WORD_DIR + "Solution.java";
        this.COMPILE_ERROR = WORD_DIR + "compileError.txt";
        this.STDOUT = WORD_DIR + "stdout.txt";
        this.STDERR = WORD_DIR + "stderr.txt";
    }

    /**
     * 编译 + 运行
     *
     * @param question 需要编译运行的 java 源代码
     * @return 编译运行的结果
     */
    public Answer compileAndRun(Question question) {
        Answer answer = new Answer();
        // 1. 初始化临时文件目录
        File workDir = new File(WORD_DIR);
        if (!workDir.exists()) {
            workDir.mkdirs();
        }
        // 进行安全性判定
        String questionCode = question.getCode();
        if (!checkCodeSafe(questionCode)) {
            answer.setError(3);
            answer.setReason("您提交的代码不符合要求！");
            return answer;
        }
        // 2. 把 question 中的 code 写入到 Solution.java 文件中
        FileUtil.writerFile(CODE, questionCode);
        // 3. 创建子进程，调用 javac 进行编译.java文件。如果编译出错，javac 会把错误信息写入 stderr 里， 使用compileError.txt
        String compileCmd = String.format("javac -encoding utf8 %s -d %s", CODE, WORD_DIR);
        CommandUtil.run(compileCmd, null, COMPILE_ERROR);
        String compileError = FileUtil.readFile(COMPILE_ERROR);
        if (!compileError.isEmpty()) {
            // 编译出错
            answer.setError(1);
            answer.setReason(compileError);
            return answer;
        }
        // 4. 创建子进程，调用 java 命令并执行。运行程序的时候，也会把 java 子进程的标准输出和标准错误获取到：stdout.txt, stderr.txt
        String runCmd = String.format("java -classpath %s %s", WORD_DIR, CLASS_NAME);
        CommandUtil.run(runCmd, STDOUT, STDERR);
        String stdErr = FileUtil.readFile(STDERR);
        if (!stdErr.isEmpty()) {
            // 运行出错
            answer.setError(2);
            answer.setStderr(stdErr);
            return answer;
        }
        // 5. 父进程获取编译执行结果打包成 entity.Answer 对象返回。编译执行的结果，就通过刚才约定的这几个文件来进行获取即可
        answer.setError(0);
        answer.setStdout(FileUtil.readFile(STDOUT));
        return answer;
    }

    private boolean checkCodeSafe(String code) {
        List<String> blackList = new ArrayList<>();
        blackList.add("Runtime");
        blackList.add("exec");
        blackList.add("java.io");
        blackList.add("java.net");
        for (String target : blackList) {
            int pos = code.indexOf(target);
            if (pos >= 0) {
                return false;
            }
        }
        return true;
    }
}
