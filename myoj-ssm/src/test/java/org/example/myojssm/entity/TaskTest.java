package org.example.myojssm.entity;

import org.junit.jupiter.api.Test;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-01-27
 * Time: 22:09
 */
class TaskTest {

    @Test
    void compileAndRun() {
        Task task = new Task();
        Question question = new Question();
        question.setCode("public class Solution {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello World\");\n" +
                "    }\n" +
                "}");
        task.compileAndRun(question);
    }
}