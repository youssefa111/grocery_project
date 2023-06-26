package com.grocery_project.project.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrderRequestDto implements Serializable {
    @JsonIgnore
    private final LocalDate orderDate = LocalDate.now();
    @JsonIgnore
    private final Boolean isDelivered = false;
    @JsonIgnore
    private final BigDecimal totalAmount;
    @NotNull
    private final Long usersId;

}
