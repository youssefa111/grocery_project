package com.grocery_project.core.listeners;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import java.beans.PropertyDescriptor;

public class LowercaseEntityListener {

    @PrePersist
    @PreUpdate
    public void prePersist(Object entity) {
        // Check if the entity has string properties and lowercase them
        BeanWrapper entityWrapper = PropertyAccessorFactory.forBeanPropertyAccess(entity);
        PropertyDescriptor[] propertyDescriptors = entityWrapper.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (propertyDescriptor.getPropertyType() == String.class) {
                String propertyName = propertyDescriptor.getName();
                String propertyValue = (String) entityWrapper.getPropertyValue(propertyName);
                if (propertyValue != null) {
                    entityWrapper.setPropertyValue(propertyName, propertyValue.toLowerCase());
                }
            }
        }
    }
}