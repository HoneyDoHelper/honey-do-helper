package com.codeup.honeydohelper.Repositories;

import com.codeup.honeydohelper.Models.HoneydoerReviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoneydoerReviewsRepository extends JpaRepository<HoneydoerReviews, Integer> {
    List<HoneydoerReviews> findAllByHoneydoer_Id(int honeydoerId);
}
