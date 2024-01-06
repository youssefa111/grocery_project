package com.grocery_project.core.annotations.auth;

import com.grocery_project.core.constant.AppConstants;
import org.springframework.security.access.annotation.Secured;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Secured(AppConstants.ADMIN)
public @interface IsAdmin {
}
