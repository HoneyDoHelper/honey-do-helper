package com.codeup.honeydohelper.Repositories;
import com.codeup.honeydohelper.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findById(int id);
    Role findByName(String name);
}
