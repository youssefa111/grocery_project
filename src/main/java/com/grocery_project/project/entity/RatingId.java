package com.grocery_project.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RatingId implements Serializable {
    private static final long serialVersionUID = 6664189763066280459L;
    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;
    @Column(name = "USERS_ID", nullable = false)
    private Long usersId;

    public Long getUsersId() {
        return usersId;
    }

    public void setUsersId(Long usersId) {
        this.usersId = usersId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, usersId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RatingId entity = (RatingId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.usersId, entity.usersId);
    }
}