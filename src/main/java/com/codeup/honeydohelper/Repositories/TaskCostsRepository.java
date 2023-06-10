package com.codeup.honeydohelper.Repositories;

import com.codeup.honeydohelper.Models.TaskCosts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCostsRepository extends JpaRepository<TaskCosts, Integer> {
    TaskCosts findByTask_Id(int taskId);
}
