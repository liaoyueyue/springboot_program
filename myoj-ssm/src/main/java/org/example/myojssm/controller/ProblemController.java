package org.example.myojssm.controller;

import jakarta.validation.constraints.NotNull;
import org.example.myojssm.common.Result;
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
public class ProblemController {
    @Autowired
    private ProblemService problemservice;

    @PostMapping("/add")
    public Result addProblem(@RequestBody @Validated(Problem.Add.class) Problem problem) {
        System.out.println(problem);
        return problemservice.addProblem(problem);
    }

    @DeleteMapping("/delete")
    public Result deleteProblem(@RequestParam @NotNull int id) {
        return problemservice.deleteProblem(id);
    }

    @PutMapping("/update")
    public Result updateProblem(@RequestBody @Validated(Problem.Update.class) Problem problem) {
        return problemservice.updateProblem(problem);
    }

    @GetMapping("/list")
    public Result showProblemListByCollectionOrLevel(@NotNull Integer pageNum, @NotNull Integer pageSize, @RequestParam(required = false) String collectionName, @RequestParam(required = false) String level) {
        return problemservice.getProblemList(pageNum, pageSize, collectionName, level);
    }

    @GetMapping("/all")
    public Result showAllProblem() {
        List<Problem> problems = problemservice.queryAllProblem();
        return Result.success(problems);
    }

    @GetMapping("/detail")
    public Result showProblemById(@RequestParam @NotNull int id) {
        Problem problem = problemservice.queryProblemById(id);
        return problem != null ? Result.success(problem) : Result.fail();
    }
}
