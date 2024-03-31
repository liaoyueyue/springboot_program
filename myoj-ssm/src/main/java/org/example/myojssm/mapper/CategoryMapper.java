package org.example.myojssm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.myojssm.common.Result;
import org.example.myojssm.entity.Category;

import java.util.List;

/**
* @author liaoyueyuedescription
* @description 针对表【category】的数据库操作Mapper
* @createDate 2024-03-31 12:32:23
* @Entity org.example.myojssm.entity.Category
*/
@Mapper
public interface CategoryMapper {

    /**
     * 新增一个分类
     * @param category 分类实体
     * @return 数据库影响行数
     */
    int insertCategory(Category category);

    /**
     * 查询分类列表
     * @return 所有分类列表
     */
    List<Category> queryAllCategory();

    /**
     * 查询分类使用编号
     * @param id 分类编号
     * @return 编号对应分类
     */
    Category queryCategoryById(@Param("id") int id);

    /**
     * 更新分类使用编号
     * @param category 分类实体
     * @return 数据库影响行数
     */
    int updateCategoryById(Category category);

}




