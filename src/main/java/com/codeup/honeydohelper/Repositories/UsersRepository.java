package com.codeup.honeydohelper.Repositories;

import com.codeup.honeydohelper.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
