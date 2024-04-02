package org.example.myojssm.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.example.myojssm.common.Result;
import org.example.myojssm.entity.PageBean;
import org.example.myojssm.entity.Problem;
import org.example.myojssm.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 题目服务层
 * User: liaoyueyue
 * Date: 2024-02-27
 * Time: 23:44
 */
@RestController
@RequestMapping("/problem")
@CrossOrigin
public class ProblemController {
    @Autowired
    private ProblemService problemservice;

    @GetMapping("/add")
    public Result addProblem(@Validated(Problem.Add.class) Problem problem) {
        return problemservice.addProblem();
    }

    @GetMapping("/all")
    public Result showAllProblem() {
        List<Problem> problems = problemservice.queryAllProblem();
        return Result.success(problems);
    }

    @GetMapping("/detail")
    public Result showProblemById(Integer id) {
        if (id == null || id <= 0) {
            return Result.fail();
        }
        Problem problem = problemservice.queryProblemById(id);
        problem.setTestCode(null);
        if (problem != null) {
            return Result.success(problem);
        }
        return Result.fail();
    }

    @GetMapping("/list")
    public Result showProblemListByCategoryOrLevel(@NotNull Integer pageNum, @NotNull Integer pageSize, @RequestParam(required = false) Integer categoryId, @RequestParam(required = false) String level) {
        return problemservice.list(pageNum, pageSize, categoryId, level);
    }
}
