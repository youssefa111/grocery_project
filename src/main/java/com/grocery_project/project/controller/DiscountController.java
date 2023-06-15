package com.grocery_project.project.controller;


import com.grocery_project.core.constant.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${baseUrl}/discount")
@Validated
@Secured(AppConstants.ADMIN)
public class DiscountController {
}
