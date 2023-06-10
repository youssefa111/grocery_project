package com.grocery_project.project.mapper;


import com.grocery_project.project.dto.product.ProductRequestDTO;
import com.grocery_project.project.dto.product.ProductResponseDTO;
import com.grocery_project.project.dto.product.ProductUpdateDTO;
import com.grocery_project.project.entity.Product;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
                CategoryMapper.class,
                QuantityMapper.class,
                DiscountMapper.class
        }
)
public interface ProductMapper {

        Product toEntity(ProductRequestDTO productRequestDTO);
        Product toEntity(ProductUpdateDTO productUpdateDTO);
        ProductResponseDTO toDTO(Product category);
}
