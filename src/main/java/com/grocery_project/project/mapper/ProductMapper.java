package com.grocery_project.project.mapper;


import com.grocery_project.project.dto.product.ProductRequestDTO;
import com.grocery_project.project.dto.product.ProductResponseDTO;
import com.grocery_project.project.dto.product.ProductUpdateDTO;
import com.grocery_project.project.entity.Product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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


        @Mapping(target="quantity", source="productRequestDTO.quantityRequestDTO")
        @Mapping(target="category", source="productRequestDTO.categoryUpdateDTO")
        Product toEntity(ProductRequestDTO productRequestDTO);
        Product toEntity(ProductUpdateDTO productUpdateDTO);
        @Mapping(target="categoryResponseDTO", source="product.category")
        @Mapping(target="discountResponseDTO", source="product.discount")
        @Mapping(target="quantityResponseDTO", source="product.quantity")
        ProductResponseDTO toDTO(Product product);
}
