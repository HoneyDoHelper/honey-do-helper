package com.codeup.honeydohelper.Repositories;

import com.codeup.honeydohelper.Models.HoneyUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoneyUsersRepository extends JpaRepository<HoneyUsers, Integer> {
    HoneyUsers findByEmail(String email);
    HoneyUsers findTopByOrderByIdDesc();

    List<HoneyUsers> findAllByHoneyUsers_Id(int id);
}
