package com.codeup.honeydohelper.Repositories;

import com.codeup.honeydohelper.Models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks, Integer> {
}
