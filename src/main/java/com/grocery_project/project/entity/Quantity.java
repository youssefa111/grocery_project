package com.grocery_project.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "QUANTITY")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Quantity {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ITEM_QNT", nullable = false)
    private Long itemQnt;

    @Column(name = "MIN_QNT", nullable = false)
    private Long minQnt;

}