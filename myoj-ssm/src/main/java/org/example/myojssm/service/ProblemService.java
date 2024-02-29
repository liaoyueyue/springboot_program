package org.example.myojssm.service;

import org.example.myojssm.entity.Problem;
import org.example.myojssm.mapper.ProblemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-02-27
 * Time: 23:39
 */

@Service
public class ProblemService {
    @Autowired
    ProblemMapper problemMapper;
    public List<Problem> queryAllProblem() {
        return problemMapper.queryAllProblem();
    }

    public Problem queryProblemById(Integer id) {
        return problemMapper.queryProblemById(id);
    }
}
