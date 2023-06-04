package com.codeup.honeydohelper.Repositories;

import com.codeup.honeydohelper.Models.Chats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatsRepository extends JpaRepository<Chats, Integer> {
}
