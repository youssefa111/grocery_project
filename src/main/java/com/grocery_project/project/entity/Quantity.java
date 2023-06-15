package com.grocery_project.project.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ITEM_QNT", nullable = false)
    private Long itemQnt;

    @Column(name = "MIN_QNT", nullable = false)
    private Long minQnt;

}