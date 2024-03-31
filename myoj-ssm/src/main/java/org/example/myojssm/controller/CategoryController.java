package org.example.myojssm.controller;

import org.example.myojssm.common.Result;
import org.example.myojssm.entity.Category;
import org.example.myojssm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-31
 * Time: 12:39
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Result addCategory(@Validated(Category.Add.class) Category category) {
        return categoryService.addCategory(category);
    }

    @GetMapping("/list")
    public Result getCategoryList() {
        return categoryService.getCategoryList();
    }

    @PostMapping("/update")
    public Result updateCategory(@Validated(Category.Update.class) Category category) {
        return categoryService.updateCategory(category);
    }
}
