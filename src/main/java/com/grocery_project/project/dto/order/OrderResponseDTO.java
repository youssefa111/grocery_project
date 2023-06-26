package com.grocery_project.project.dto.order;

import com.grocery_project.auth.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrderResponseDTO {

    private Long id;

    private LocalDate orderDate;

    private Boolean isDelivered;

    private BigDecimal totalAmount;

    private Long  usersId;
}
