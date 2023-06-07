package com.codeup.honeydohelper.Repositories;

import com.codeup.honeydohelper.Models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserProfile, Integer> {
}
