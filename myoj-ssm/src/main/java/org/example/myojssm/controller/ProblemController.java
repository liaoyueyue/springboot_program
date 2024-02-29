package org.example.myojssm.controller;

import org.example.myojssm.common.AjaxResult;
import org.example.myojssm.entity.Problem;
import org.example.myojssm.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-02-27
 * Time: 23:44
 */
@RestController
@RequestMapping("/problem")
public class ProblemController {
    @Autowired
    ProblemService problemservice;
    @GetMapping("/all")
    public AjaxResult showProblemList() {
        List<Problem> problems = problemservice.queryAllProblem();
        return AjaxResult.success(problems);
    }

    @GetMapping("/detail")
    public AjaxResult showProblemById(Integer id) {
        if (id == null || id <= 0) {
            return AjaxResult.fail(-1, "illegal request");
        }
        Problem problem = problemservice.queryProblemById(id);
        problem.setTestCode(null);
        if (problem != null) {
            return AjaxResult.success(problem);
        }
        return AjaxResult.fail(-1, "illegal request");
    }
}
