package org.example.myojssm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.myojssm.entity.Problem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-02-27
 * Time: 23:18
 */
@Mapper
public interface ProblemMapper {
    /**
     * 查询全部题目
     * @return 所有题目
     */
    List<Problem> queryAllProblem();

    /**
     * 根据等级查询全部题目
     * @return 符合等级的所有题目
     */
    List<Problem> queryAllByLevel(@Param("level") String level);

    /**
     * 使用 id 查询题目
     * @return id 对应题目详情
     */
    Problem queryOneById(@Param("id") Integer id);

    /**
     * 多条件查询题目列表
     * @param level 题目等级
     * @param title 题目标题
     * @return 符合条件的题目列表
     */
    List<Problem> queryAllByCriteria(@Param("level") String level, @Param("title") String title);
}
