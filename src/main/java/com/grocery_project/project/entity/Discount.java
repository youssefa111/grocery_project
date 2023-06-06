package com.grocery_project.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "DISCOUNT")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Discount {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DISC_PERCENT", nullable = false)
    private Integer discPercent;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Column(name = "EXPIRE_DATE", nullable = false)
    private LocalDate expireDate;

}