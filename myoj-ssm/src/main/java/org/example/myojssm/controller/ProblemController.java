package org.example.myojssm.controller;

import org.example.myojssm.entity.Problem;
import org.example.myojssm.service.Problemservice;
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
    Problemservice problemservice;
    @GetMapping("/all")
    public List<Problem> queryAllProblem() {
        List<Problem> problems = problemservice.queryAllProblem();
        return problems;
    }
}
