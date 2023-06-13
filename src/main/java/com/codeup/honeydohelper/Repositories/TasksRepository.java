package com.codeup.honeydohelper.Repositories;

import com.codeup.honeydohelper.Models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<Tasks, Integer> {
    List<Tasks> findAllByHoneydoerService_Id(int honeydoerServiceId);
    Tasks findTopByOrderByIdDesc();

    List<Tasks> findAllByUser_Id(int user_Id);

}
