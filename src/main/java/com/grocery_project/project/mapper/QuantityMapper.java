package com.grocery_project.project.mapper;


import com.grocery_project.project.dto.quantity.QuantityRequestDTO;
import com.grocery_project.project.dto.quantity.QuantityResponseDTO;
import com.grocery_project.project.dto.quantity.QuantityUpdateDTO;
import com.grocery_project.project.entity.Quantity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface QuantityMapper {

    Quantity toEntity(QuantityRequestDTO quantityRequestDTO);
    Quantity toEntity(QuantityUpdateDTO quantityUpdateDTO);
    QuantityResponseDTO toDTO(Quantity category);
}
