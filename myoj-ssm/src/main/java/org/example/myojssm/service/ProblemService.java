package org.example.myojssm.service;

import org.example.myojssm.entity.Problem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-23
 * Time: 13:29
 */
public interface ProblemService {
    List<Problem> queryAllProblem();

    Problem queryProblemById(Integer id);

    List<Problem> queryAllByLevel(String level);

    List<Problem> queryAllByCriteria(String level, String title);
}
