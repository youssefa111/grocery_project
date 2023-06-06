package com.grocery_project.project.repository;

import com.grocery_project.project.entity.Rating;
import com.grocery_project.project.entity.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, RatingId> {
}