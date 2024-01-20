package com.grocery_project.project.entity;

import com.grocery_project.core.listeners.LowercaseEntityListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "PRODUCT")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({LowercaseEntityListener.class, AuditingEntityListener.class})
@DynamicUpdate
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false, length = 500)
    private String description;

    @Column(name = "PRICE", nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(name = "IS_STOCKED")
    private Boolean isStocked;

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "IMAGE_URL", length = 4000)
    private String imageUrl;

    @Column(name = "PURCHASE_NUM")
    private Long purchaseNum;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "QUANTITY_ID", nullable = false)
    private Quantity quantity;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DISCOUNT_ID")
    private Discount discount;

    @Column(name = "CREATED_AT")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    private Instant updatedAt;

    @CreatedBy
    @Column(
            nullable = false,
            updatable = false
    )
    private Long createdBy;

    @LastModifiedBy
    @Column(insertable = false)
    private Long lastModifiedBy;


}