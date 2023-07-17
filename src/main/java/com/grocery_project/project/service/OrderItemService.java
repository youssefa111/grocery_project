package com.grocery_project.project.service;

import com.grocery_project.core.exception_handling.exception.InvalidDataEntryException;
import com.grocery_project.project.dto.orderItems.OrderItemRequestDTO;
import com.grocery_project.project.dto.orderItems.OrderItemResponseDTO;
import com.grocery_project.project.entity.Discount;
import com.grocery_project.project.entity.OrderItem;
import com.grocery_project.project.entity.Product;
import com.grocery_project.project.mapper.OrderItemMapper;
import com.grocery_project.project.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final ProductService productService;

    @Transactional
    public List<OrderItemResponseDTO> saveAll(List<OrderItemRequestDTO> orderItemRequestDTOList, Long id) {

        // Init list of order items entities to save into database
        List<OrderItem> entities = new ArrayList<>();
        // loop into dtoList to make processes
          orderItemRequestDTOList.forEach(orderItemRequestDTO -> {

              // First: get the product with productId To can get price of product and minus the quantity of it inside the database.
              Product product = productService.getById(orderItemRequestDTO.getProductId());
              // Second: get discount entity of the product to check first if there is discount on the product or no
              Discount discount = product.getDiscount();

              // Third: make some validation needed for the product before save order items records into database
              if (product.getQuantity().getItemQnt() < orderItemRequestDTO.getQuantity())
                  throw new InvalidDataEntryException("Invalid Data entry!,The quantity of {" +product.getName() + "} is more than quantity of it in inventory, please try order less than the available quantity");

              if (discount != null && discount.getIsActive())
                  orderItemRequestDTO.setPricePerUnit(product.getPrice().multiply(new BigDecimal(product.getDiscount().getDiscPercent())).divide(new BigDecimal(100)));
              else
                  orderItemRequestDTO.setPricePerUnit(product.getPrice());

              product.getQuantity().setItemQnt(product.getQuantity().getItemQnt() - 1);

              orderItemRequestDTO.setOrderId(id);
              entities.add(orderItemMapper.orderItemRequestDTOToOrderItem(orderItemRequestDTO));

          });
          // convert the result entities into Response DTOS  to back them to API response
        List<OrderItemResponseDTO> orderItemResponseDTOS  = new ArrayList<>();
        var result = orderItemRepository.saveAll(entities);
        result.forEach(orderItem -> orderItemResponseDTOS.add(orderItemMapper.orderItemToOrderItemResponseDTO(orderItem)));
        return  orderItemResponseDTOS;
    }
}
