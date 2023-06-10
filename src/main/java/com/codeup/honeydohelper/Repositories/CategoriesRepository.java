package com.codeup.honeydohelper.Repositories;

import com.codeup.honeydohelper.Models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepository extends JpaRepository <Categories, Integer> {

}
