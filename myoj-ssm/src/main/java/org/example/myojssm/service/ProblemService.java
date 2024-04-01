package org.example.myojssm.service;

import org.example.myojssm.common.Result;
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
    /**
     * 查询所有题目列表
     * @return 题目列表
     */
    List<Problem> queryAllProblem();

    /**
     * 查询题目使用编号
     * @param id 编号
     * @return 对应编号的题目
     */
    Problem queryProblemById(Integer id);

    /**
     * 查询指定等级的题目
     * @param level 等级
     * @return 对应等级的题目列表
     */
    List<Problem> queryAllByLevel(String level);

    /**
     * 查询所有题目通过一定标准
     * @param level 等级
     * @param title 题目
     * @return 对应标准的题目
     */
    List<Problem> queryAllByCriteria(String level, String title);

    /**
     * 添加题目
     * @return 是否添加成功
     */
    Result addProblem();
}
