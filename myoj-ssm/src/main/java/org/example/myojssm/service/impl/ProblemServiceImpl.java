package org.example.myojssm.service.impl;

import org.example.myojssm.common.Result;
import org.example.myojssm.entity.Problem;
import org.example.myojssm.mapper.ProblemMapper;
import org.example.myojssm.service.ProblemService;
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
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public List<Problem> queryAllProblem() {
        return problemMapper.queryAllProblem();
    }

    @Override
    public Problem queryProblemById(Integer id) {
        return problemMapper.queryOneById(id);
    }

    @Override
    public List<Problem> queryAllByLevel(String level) {
        return problemMapper.queryAllByLevel(level);
    }

    @Override
    public List<Problem> queryAllByCriteria(String level, String title) {
        return problemMapper.queryAllByCriteria(level, title);
    }

    @Override
    public Result addProblem() {
        return problemMapper.insertProblem()>0?Result.success():Result.fail();
    }
}
