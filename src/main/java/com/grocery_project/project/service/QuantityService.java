package com.grocery_project.project.service;

import com.grocery_project.core.base.BaseResponse;
import com.grocery_project.core.constant.AppConstants;
import com.grocery_project.core.exception_handling.exception.RecordNotFoundException;
import com.grocery_project.project.dto.quantity.QuantityRequestDTO;
import com.grocery_project.project.dto.quantity.QuantityUpdateDTO;
import com.grocery_project.project.entity.Category;
import com.grocery_project.project.entity.Quantity;
import com.grocery_project.project.mapper.QuantityMapper;
import com.grocery_project.project.repository.QuantityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuantityService {

    private final QuantityRepository quantityRepository;
    private final QuantityMapper quantityMapper;

    @Transactional
    public BaseResponse<Quantity> save(QuantityRequestDTO quantityRequestDTO) {
        Quantity entity = quantityMapper.toEntity(quantityRequestDTO);
        var result =quantityRepository.save(entity);
        return  new BaseResponse<>(result,"This quantity is created Successfully!", AppConstants.CREATE_STATUS);
    }

    @Transactional
    public BaseResponse<Quantity> update(QuantityUpdateDTO quantityUpdateDTO) {
        Optional<Quantity> result = quantityRepository.findById(quantityUpdateDTO.getId());
        if(result.isPresent()){
            var entity = result.get();
          quantityMapper.updateQuantityFromDto(quantityUpdateDTO,entity);
            entity =quantityRepository.save(entity);
            return  new BaseResponse<>(entity,"This quantity is updated Successfully!");
        }else {
            throw new RecordNotFoundException("this quantity with id:-{"+ quantityUpdateDTO.getId() + "} not found");
        }
    }
}
