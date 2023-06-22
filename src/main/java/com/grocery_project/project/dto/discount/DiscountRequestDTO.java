package com.grocery_project.project.dto.discount;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountRequestDTO {


    @NotNull
    private Long productId;
    private Boolean isActive = true;
    @Min(1)
    @Max(95)
    @NotNull
    private Integer discPercent;
    @NotNull
    @JsonFormat( pattern="yyyy-MM-dd")
    private LocalDate expireDate ;
}
