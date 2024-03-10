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
     * 使用 id 查询题目
     * @return id 对应题目详情
     */
    Problem queryOneById(@Param("id") Integer id);
}
