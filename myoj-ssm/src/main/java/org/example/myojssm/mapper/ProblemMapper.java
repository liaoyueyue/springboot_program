package org.example.myojssm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.myojssm.entity.Problem;
import org.example.myojssm.entity.vo.ProblemVo;

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
     * 新增题目
     *
     * @param problem 题目实体
     * @return 数据库影响行数
     */
    int insertProblem(Problem problem);

    /**
     * 查询全部题目
     *
     * @return 所有题目
     */
    List<Problem> queryAllProblem();

    /**
     * 根据等级查询全部题目
     *
     * @return 符合等级的所有题目
     */
    List<Problem> queryAllByLevel(@Param("level") String level);

    /**
     * 使用 id 查询题目
     *
     * @return id 对应题目详情
     */
    Problem queryOneById(@Param("id") Integer id);

    /**
     * 多条件查询题目列表
     *
     * @param level 题目等级
     * @param title 题目标题
     * @return 符合条件的题目列表
     */
    List<Problem> queryAllByCriteria(@Param("level") String level, @Param("title") String title);

    /**
     * 查询题目列表 通过合集名称等级
     *
     * @param collectionName 题目合集名称
     * @param level          题目等级
     * @return 符合条件的题目列表
     */
    List<ProblemVo> queryProblemListByColAndLevel(@Param("collectionName") String collectionName, @Param("level") String level);
}
