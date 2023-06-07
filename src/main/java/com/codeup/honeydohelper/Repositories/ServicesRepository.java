package com.codeup.honeydohelper.Repositories;

import com.codeup.honeydohelper.Models.Categories;
import com.codeup.honeydohelper.Models.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Services, Integer> {
    List<Services>findAllByCategory_Id(int categoryId);

}
