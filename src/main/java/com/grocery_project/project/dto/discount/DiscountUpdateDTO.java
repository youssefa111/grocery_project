package com.grocery_project.project.dto.discount;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountUpdateDTO {

    @NotNull
    private Long id;
    private Boolean isActive;
    @Min(1)
    @Max(95)
    private Integer discPercent;
    @JsonFormat( pattern="yyyy-MM-dd")
    private LocalDate expireDate ;
}
