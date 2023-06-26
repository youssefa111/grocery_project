package com.grocery_project.project.mapper;

import com.grocery_project.project.dto.order.OrderRequestDto;
import com.grocery_project.project.dto.order.OrderResponseDTO;
import com.grocery_project.project.entity.Order;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    @Mapping(source = "usersId", target = "users.id")
    Order orderDtoToOrder(OrderRequestDto orderRequestDto);

    @Mapping(source = "users.id", target = "usersId")
    OrderResponseDTO orderToOrderDto(Order order);

    @Mapping(source = "usersId", target = "users.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrderFromOrderDto(OrderRequestDto orderRequestDto, @MappingTarget Order order);
}
