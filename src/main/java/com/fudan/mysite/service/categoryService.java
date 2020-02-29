package com.fudan.mysite.service;

import com.fudan.mysite.entity.Category;

import java.util.List;

public interface categoryService {
    Category findCategoryByCategoryName(String category);

    void saveCategory(Category category);

    List<Category> findAll();

    Category findCategoryByCategoryId(Integer id);

    void deleteByCategoryId(Integer id);
}
