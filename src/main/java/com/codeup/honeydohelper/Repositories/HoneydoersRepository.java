package com.codeup.honeydohelper.Repositories;

import com.codeup.honeydohelper.Models.Honeydoers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoneydoersRepository extends JpaRepository<Honeydoers, Integer> {

    Honeydoers findTopByOrderByIdDesc();
    Honeydoers findByUser_Id(int userId);

}
