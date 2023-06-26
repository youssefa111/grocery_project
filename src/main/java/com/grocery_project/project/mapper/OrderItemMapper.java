package com.grocery_project.project.mapper;

import com.grocery_project.project.dto.orderItems.OrderItemRequestDTO;
import com.grocery_project.project.dto.orderItems.OrderItemResponseDTO;
import com.grocery_project.project.entity.OrderItem;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {
    @Mapping(source = "orderId", target = "order.id")
    @Mapping(source = "productId", target = "product.id")
    OrderItem orderItemRequestDTOToOrderItem(OrderItemRequestDTO orderItemRequestDTO);

    @InheritInverseConfiguration(name = "orderItemResponseDTOToOrderItem")
    OrderItemResponseDTO orderItemToOrderItemResponseDTO(OrderItem orderItem);

    @InheritConfiguration(name = "orderItemRequestDTOToOrderItem")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrderItemFromOrderItemRequestDTO(OrderItemRequestDTO orderItemRequestDTO, @MappingTarget OrderItem orderItem);
}
