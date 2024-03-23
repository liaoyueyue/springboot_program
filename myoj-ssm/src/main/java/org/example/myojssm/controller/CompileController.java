package org.example.myojssm.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.example.myojssm.common.Result;
import org.example.myojssm.common.exception.CodeVaildException;
import org.example.myojssm.common.exception.ProblemNotFoundException;
import org.example.myojssm.entity.Answer;
import org.example.myojssm.entity.Problem;
import org.example.myojssm.entity.Question;
import org.example.myojssm.entity.Task;
import org.example.myojssm.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-02-29
 * Time: 22:57
 */
@RestController
public class CompileController {
    @Autowired
    private ProblemService problemService;

    @AllArgsConstructor
    static class CompileRequest {
        public int id;
        public String code;
    }

    static class CompileResponse {
        public int error; // 规定 error 为 0 表示编译成功，为 1 表示编译出错，为 2 表示运行异常（用户提交代码异常）， 3 表示其他错误
        public String reason;
        public String stdout;
    }

    @PostMapping("/compile")
    public Result compileProblem(HttpServletRequest req, HttpServletResponse resp, int id, String code) {
        CompileRequest compileRequest = null;
        CompileResponse compileResponse = new CompileResponse();
        try {
            // 1. 读取请求正文，按照JSON格式解析
            compileRequest = new CompileRequest(id, code);
            // 2. 从数据库中查询题目详情获取测试代码
            Problem problem = problemService.queryProblemById(compileRequest.id);
            if (problem == null) {
                throw new ProblemNotFoundException();
            }
            String testCode = problem.getTestCode(); // 测试代码
            String requestCode = compileRequest.code; // 用户代码
            // 3. 将用户代码和测试代码进行拼接
            String finalCode = mergeCode(requestCode, testCode);
            if (finalCode == null) {
                throw new CodeVaildException();
            }
            // 4. 创建 Task 实例编译并运行代码
            Task task = new Task();
            Question question = new Question(finalCode);
            Answer answer = task.compileAndRun(question);
            // 5. 根据响应结果 Answer 进行返回
            compileResponse.error = answer.getError();
            compileResponse.reason = answer.getReason();
            compileResponse.stdout = answer.getStdout();
        } catch (ProblemNotFoundException e) {
            compileResponse.error = 3;
            compileResponse.reason = "没有找到指定的题目 id: " + compileRequest.id;
        } catch (CodeVaildException e) {
            compileResponse.error = 3;
            compileResponse.reason = "提交的代码不符合要求！";
        } finally {
            return Result.success(compileResponse);
        }
    }


    private static String mergeCode(String requestCode, String testCode) {
        int pos = requestCode.lastIndexOf("}");
        if (pos == -1) {
            return null;
        }
        return requestCode.substring(0, pos) + testCode + "\n}";
    }
}
