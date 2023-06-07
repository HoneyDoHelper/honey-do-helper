package com.codeup.honeydohelper.Repositories;

import com.codeup.honeydohelper.Models.UserDetails;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
}
