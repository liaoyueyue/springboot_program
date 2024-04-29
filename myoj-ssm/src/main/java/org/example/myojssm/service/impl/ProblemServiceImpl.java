package org.example.myojssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.myojssm.common.Result;
import org.example.myojssm.entity.PageBean;
import org.example.myojssm.entity.Problem;
import org.example.myojssm.entity.vo.ProblemVo;
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
        return problemMapper.queryProblemById(id);
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
    public Result addProblem(Problem problem) {
        return problemMapper.insertProblem(problem) > 0 ? Result.success() : Result.fail();
    }

    @Override
    public Result getProblemList(Integer pageNum, Integer pageSize, String collectionName, String level) {
        if (pageNum < 1 || pageSize > 20) {
            return Result.fail("Illegal parameters");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ProblemVo> problems = problemMapper.queryProblemListByColAndLevel(collectionName, level);
        Page<ProblemVo> page = (Page<ProblemVo>) problems;
        return Result.success(new PageBean<>(page.getTotal(), page.getResult()));
    }

    @Override
    public Result deleteProblem(int id) {
        if (id <= 0) {
            return Result.fail();
        }
        return problemMapper.deleteProblemById(id) > 0 ? Result.success() : Result.fail();
    }

    @Override
    public Result updateProblem(Problem problem) {
        return problemMapper.updateProblemById(problem) > 0 ? Result.success() : Result.fail();
    }
}
