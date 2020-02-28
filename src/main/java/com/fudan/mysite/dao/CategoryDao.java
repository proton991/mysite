package com.fudan.mysite.dao;

import com.fudan.mysite.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Integer> {
    Category findCategoryByCategoryName(String category);

    Category findCategoryByCategoryId(Integer id);
}
