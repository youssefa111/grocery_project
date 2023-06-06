package com.grocery_project.project.repository;

import com.grocery_project.project.entity.OrderItem;
import com.grocery_project.project.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}