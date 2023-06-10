package com.grocery_project.project.mapper;

import com.grocery_project.project.dto.discount.DiscountRequestDTO;
import com.grocery_project.project.dto.discount.DiscountResponseDTO;
import com.grocery_project.project.dto.discount.DiscountUpdateDTO;
import com.grocery_project.project.entity.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface DiscountMapper {

    Discount toEntity(DiscountRequestDTO discountRequestDTO);
    Discount toEntity(DiscountUpdateDTO discountUpdateDTO);
    DiscountResponseDTO toDTO(Discount category);
}
