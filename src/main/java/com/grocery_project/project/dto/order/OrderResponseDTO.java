package com.grocery_project.project.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private Long id;

    private LocalDate orderDate;

    private Boolean isDelivered;

    private BigDecimal totalAmount;

//    private String usersId;
//
//    public void setUsersId(Long id) {
//        this.usersId = CryptoUtils.encrypt(id);
//
//    }
}
