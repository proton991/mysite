package com.fudan.mysite.service.impl;

import com.fudan.mysite.dao.CategoryDao;
import com.fudan.mysite.entity.Category;
import com.fudan.mysite.service.categoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class categoryServiceImpl implements categoryService {

    @Resource
    private CategoryDao categoryDao;

    @Override
    public Category findCategoryByCategoryName(String category) {
        return categoryDao.findCategoryByCategoryName(category);
    }

    @Override
    public void saveCategory(Category category) {
        categoryDao.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findCategoryByCategoryId(Integer id) {
        return categoryDao.findCategoryByCategoryId(id);
    }
}
