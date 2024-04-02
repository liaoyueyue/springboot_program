package org.example.myojssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.myojssm.common.Result;
import org.example.myojssm.entity.PageBean;
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

    @Override
    public Result list(Integer pageNum, Integer pageSize, Integer categoryId, String level) {
        if (pageNum < 1 || pageSize > 5) {
            return Result.fail("Illegal parameters");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Problem> problems = problemMapper.queryCategoryList(categoryId, level);
        Page<Problem> page = (Page<Problem>) problems;
        return Result.success(new PageBean<>(page.getTotal(), page.getResult()));
    }
}
