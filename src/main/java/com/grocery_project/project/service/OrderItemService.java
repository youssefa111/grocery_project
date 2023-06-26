package com.grocery_project.project.service;

import com.grocery_project.project.dto.orderItems.OrderItemRequestDTO;
import com.grocery_project.project.dto.orderItems.OrderItemResponseDTO;
import com.grocery_project.project.entity.OrderItem;
import com.grocery_project.project.mapper.OrderItemMapper;
import com.grocery_project.project.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Transactional
    public List<OrderItemResponseDTO> saveAll(List<OrderItemRequestDTO> orderItemRequestDTOList) {

        List<OrderItem> entities = new ArrayList<>();
          orderItemRequestDTOList.forEach(orderItemRequestDTO -> entities.add(orderItemMapper.orderItemRequestDTOToOrderItem(orderItemRequestDTO)));
        List<OrderItemResponseDTO> orderItemResponseDTOS  = new ArrayList<>();
        var result = orderItemRepository.saveAll(entities);
        result.forEach(orderItem -> orderItemResponseDTOS.add(orderItemMapper.orderItemToOrderItemResponseDTO(orderItem)));
        return  orderItemResponseDTOS;
    }
}
