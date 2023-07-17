package com.grocery_project.project.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrderRequestDto implements Serializable {
    @JsonIgnore
    private  LocalDate orderDate = LocalDate.now();
    @JsonIgnore
    private  Boolean isDelivered = false;
    @JsonIgnore
    private  BigDecimal totalAmount;
    @NotNull
    private  Long usersId;

}
