package com.codeup.honeydohelper.Repositories;

import com.codeup.honeydohelper.Models.UserProfiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfilesRepository extends JpaRepository<UserProfiles, Integer> {
}
