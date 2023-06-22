package com.grocery_project.project.mapper;

import com.grocery_project.project.dto.discount.DiscountRequestDTO;
import com.grocery_project.project.dto.discount.DiscountResponseDTO;
import com.grocery_project.project.dto.discount.DiscountUpdateDTO;
import com.grocery_project.project.entity.Discount;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface DiscountMapper {

    Discount toEntity(DiscountRequestDTO discountRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDiscountFromDto(DiscountUpdateDTO discountUpdateDTO, @MappingTarget Discount discount);
    DiscountResponseDTO toDTO(Discount category);
}
