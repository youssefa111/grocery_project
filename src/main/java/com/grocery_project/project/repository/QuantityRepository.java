package com.grocery_project.project.repository;

import com.grocery_project.project.entity.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuantityRepository extends JpaRepository<Quantity, Long> {
}