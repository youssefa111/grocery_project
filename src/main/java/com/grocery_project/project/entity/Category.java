package com.grocery_project.project.entity;

import com.grocery_project.core.listeners.LowercaseEntityListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CATEGORY")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(LowercaseEntityListener.class)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CATEGORY", nullable = false, length = 30)
    private String category;
}