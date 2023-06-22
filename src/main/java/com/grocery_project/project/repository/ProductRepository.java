package com.grocery_project.project.repository;

import com.grocery_project.project.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    List<Product> findByStatusEquals(Boolean status);

    Optional<Product> findByDiscount_Id(Long id);

}