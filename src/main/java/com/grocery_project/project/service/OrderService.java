package com.grocery_project.project.service;

import com.grocery_project.core.base.BaseResponse;
import com.grocery_project.project.dto.order.CreateOrderRequestDTO;
import com.grocery_project.project.dto.order.CreateOrderResponseDTO;
import com.grocery_project.project.dto.order.OrderRequestDto;
import com.grocery_project.project.dto.order.OrderResponseDTO;
import com.grocery_project.project.dto.orderItems.OrderItemResponseDTO;
import com.grocery_project.project.mapper.OrderMapper;
import com.grocery_project.project.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

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
        OrderResponseDTO orderResponseDTO = orderMapper.orderToOrderDto(orderRepository.save(orderMapper.orderDtoToOrder(createOrderRequestDTO.getOrderRequestDto())));
        List<OrderItemResponseDTO> orderItemResponseDTOList = orderItemService.saveAll(createOrderRequestDTO.getOrderItemRequestDTO());
        CreateOrderResponseDTO createOrderResponseDTO = new CreateOrderResponseDTO(orderResponseDTO,orderItemResponseDTOList);
        return new BaseResponse<>(createOrderResponseDTO, "The request has been ordered successfully", HttpStatus.CREATED.value());
    }
}
