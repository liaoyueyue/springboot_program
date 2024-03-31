package org.example.myojssm.service;

import org.example.myojssm.common.Result;
import org.example.myojssm.entity.Category;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-31
 * Time: 12:42
 */
public interface CategoryService {
    /**
     * 新增一个分类
     * @param category 分类实体
     * @return 是否成功添加
     */
    Result addCategory(Category category);

    /**
     * 获取分类列表
     * @return 所有分类列表
     */
    Result getCategoryList();

    /**
     * 查询分类使用编号
     * @param id 分类编号
     * @return 编号对应分类
     */
    Result getCategoryById(int id);

    /**
     * 更新分类使用编号
     * @param category 分类实体
     * @return 是否更新成功
     */
    Result updateCategory(Category category);
}
