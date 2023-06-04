package com.codeup.honeydohelper.Repositories;

import com.codeup.honeydohelper.Models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository <Categories, Integer> {
}
