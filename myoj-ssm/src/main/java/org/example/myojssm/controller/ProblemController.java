package org.example.myojssm.controller;

import org.example.myojssm.common.AjaxResult;
import org.example.myojssm.entity.Problem;
import org.example.myojssm.enums.ProblemLevelEnum;
import org.example.myojssm.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
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
            return AjaxResult.fail();
        }
        Problem problem = problemservice.queryProblemById(id);
        problem.setTestCode(null);
        if (problem != null) {
            return AjaxResult.success(problem);
        }
        return AjaxResult.fail();
    }

    @GetMapping("/search")
    public AjaxResult searchProblem(@RequestParam("level") ProblemLevelEnum level, @RequestParam("title") String title) {
        if (level == null || title == null) {
            return AjaxResult.fail();
        }
        List<Problem> problems = problemservice.queryAllByCriteria(level.toString(), title.isEmpty()?null:title);
        if (problems != null) {
            return AjaxResult.success(problems);
        }
        return AjaxResult.fail();
    }
}
