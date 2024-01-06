package com.grocery_project.project.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grocery_project.core.utils.CryptoUtils;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto implements Serializable {
    @JsonIgnore
    private LocalDate orderDate = LocalDate.now();
    @JsonIgnore
    private Boolean isDelivered = false;
    @JsonIgnore
    private BigDecimal totalAmount;
    @NotNull
    private String usersId;

    public Long getUsersId() {
        return Long.parseLong(CryptoUtils.decrypt(usersId));
    }
}
