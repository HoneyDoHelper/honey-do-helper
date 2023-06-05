package com.codeup.honeydohelper.Repositories;

import com.codeup.honeydohelper.Models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
}
