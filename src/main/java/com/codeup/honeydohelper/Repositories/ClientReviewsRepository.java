package com.codeup.honeydohelper.Repositories;

import com.codeup.honeydohelper.Models.ClientReviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientReviewsRepository extends JpaRepository<ClientReviews, Integer> {
    List<ClientReviews> findAllByUser_Id(int user_Id);

    void deleteAllByUser_Id(int user_Id);
}
