package com.grocery_project.project.repository;

import com.grocery_project.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Object> findByName(String name);
}