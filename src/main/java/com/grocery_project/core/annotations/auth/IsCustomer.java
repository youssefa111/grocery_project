package com.grocery_project.core.annotations.auth;

import com.grocery_project.core.constant.AppConstants;
import org.springframework.security.access.annotation.Secured;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Secured(AppConstants.USER)
public @interface IsCustomer {
}
