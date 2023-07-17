package com.grocery_project.project.service;

import com.grocery_project.core.base.BaseResponse;
import com.grocery_project.project.dto.order.CreateOrderRequestDTO;
import com.grocery_project.project.dto.order.CreateOrderResponseDTO;
import com.grocery_project.project.dto.order.OrderRequestDto;
import com.grocery_project.project.dto.order.OrderResponseDTO;
import com.grocery_project.project.dto.orderItems.OrderItemRequestDTO;
import com.grocery_project.project.dto.orderItems.OrderItemResponseDTO;
import com.grocery_project.project.entity.Order;
import com.grocery_project.project.mapper.OrderMapper;
import com.grocery_project.project.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemService orderItemService;

    @Transactional
    public BaseResponse<CreateOrderResponseDTO> save(CreateOrderRequestDTO createOrderRequestDTO) {

        // Save Order Entity into database
        Order order = orderRepository.save(orderMapper.orderDtoToOrder(createOrderRequestDTO.getOrderRequestDto()));

        // Save order items into database
        List<OrderItemResponseDTO> orderItemResponseDTOList = orderItemService.saveAll(createOrderRequestDTO.getOrderItemRequestDTO(),order.getId());

        // Edit order's record to get totalAmount
        BigDecimal totalAmount = new BigDecimal(0);
        orderItemResponseDTOList.forEach(orderItemResponseDTO -> totalAmount.add(orderItemResponseDTO.getPricePerUnit())  );
        order.setTotalAmount(totalAmount);
        // convert entities into response DTOS and return them back to response of api
        OrderResponseDTO orderResponseDTO = orderMapper.orderToOrderDto(order);
        CreateOrderResponseDTO createOrderResponseDTO = new CreateOrderResponseDTO(orderResponseDTO,orderItemResponseDTOList);

        return new BaseResponse<>(createOrderResponseDTO, "The request has been ordered successfully", HttpStatus.CREATED.value());
    }

}
