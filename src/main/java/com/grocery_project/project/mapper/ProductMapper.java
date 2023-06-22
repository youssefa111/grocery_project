package com.grocery_project.project.mapper;


import com.grocery_project.project.dto.product.ProductRequestDTO;
import com.grocery_project.project.dto.product.ProductResponseDTO;
import com.grocery_project.project.dto.product.ProductUpdateDTO;
import com.grocery_project.project.entity.Product;

import org.mapstruct.*;

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

        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        @Mapping(target="entity.quantity", source="productUpdateDTO.quantityUpdateDTO")
        @Mapping(target="entity.category", source="productUpdateDTO.categoryUpdateDTO")
        void updateProductFromDto(ProductUpdateDTO productUpdateDTO,@MappingTarget Product entity);

        @Mapping(target="categoryResponseDTO", source="product.category")
        @Mapping(target="discountResponseDTO", source="product.discount")
        @Mapping(target="quantityResponseDTO", source="product.quantity")
        ProductResponseDTO toDTO(Product product);
}
