package com.grocery_project.project.service;

import com.grocery_project.core.base.BaseResponse;
import com.grocery_project.core.exception_handling.exception.RecordNotFoundException;
import com.grocery_project.project.dto.discount.DiscountRequestDTO;
import com.grocery_project.project.dto.discount.DiscountUpdateDTO;
import com.grocery_project.project.entity.Discount;
import com.grocery_project.project.mapper.DiscountMapper;
import com.grocery_project.project.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;
    private final ProductService productService;

    @Transactional
    public BaseResponse<Discount> save(DiscountRequestDTO discountRequestDTO) {
        Discount entity = discountMapper.toEntity(discountRequestDTO);
        var result = discountRepository.save(entity);
        productService.getById(discountRequestDTO.getProductId()).setDiscount(result);
        return new BaseResponse<>(entity, "The Discount is added Successfully!", HttpStatus.CREATED.value());
    }

    @Transactional
    public BaseResponse<Discount> update(DiscountUpdateDTO discountUpdateDTO) {
        var entity = discountRepository.findById(discountUpdateDTO.getId()).orElseThrow(() -> new RecordNotFoundException("this discount with id:-{" + discountUpdateDTO.getId() + "} not found"));
        discountMapper.updateDiscountFromDto(discountUpdateDTO, entity);
        entity = discountRepository.save(entity);
        return new BaseResponse<>(entity, "This discount is updated Successfully!");
    }

    @Transactional
    public BaseResponse<String> delete(Long id) {
        productService.findByDiscountId(id).setDiscount(null);
        discountRepository.deleteById(id);
        return new BaseResponse<>(null, "This discount with id {" + id + "} is deleted Successfully!");
    }

    @Transactional
    public BaseResponse<Discount> deactivate(Long id) {
        var entity = discountRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("this discount with id:-{" + id + "} not found"));
        entity.setIsActive(false);
        return new BaseResponse<>(entity, "This discount is deactivated Successfully!");

    }
}
