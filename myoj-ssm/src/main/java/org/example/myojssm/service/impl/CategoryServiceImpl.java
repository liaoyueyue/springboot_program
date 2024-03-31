package org.example.myojssm.service.impl;

import org.example.myojssm.common.Result;
import org.example.myojssm.entity.Category;
import org.example.myojssm.mapper.CategoryMapper;
import org.example.myojssm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-31
 * Time: 12:42
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Result addCategory(Category category) {
        return categoryMapper.insertCategory(category) > 0 ? Result.success() : Result.fail();
    }

    public Result getCategoryList() {
        return Result.success(categoryMapper.queryAllCategory());
    }

    @Override
    public Result getCategoryById(int id) {
        Category category = categoryMapper.queryCategoryById(id);
        if (category == null) {
            return Result.fail("Classification not found");
        }
        return Result.success(category);
    }

    @Override
    public Result updateCategory(Category category) {
        return categoryMapper.updateCategoryById(category)>0?Result.success():Result.fail();
    }
}
