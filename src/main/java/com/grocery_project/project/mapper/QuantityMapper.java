package com.grocery_project.project.mapper;


import com.grocery_project.project.dto.quantity.QuantityRequestDTO;
import com.grocery_project.project.dto.quantity.QuantityResponseDTO;
import com.grocery_project.project.dto.quantity.QuantityUpdateDTO;
import com.grocery_project.project.entity.Quantity;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface QuantityMapper {

    Quantity toEntity(QuantityRequestDTO quantityRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateQuantityFromDto(QuantityUpdateDTO quantityUpdateDTO,@MappingTarget Quantity quantity);

    QuantityResponseDTO toDTO(Quantity category);
}
