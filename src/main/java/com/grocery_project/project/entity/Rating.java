package com.grocery_project.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "RATINGS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @EmbeddedId
    private RatingId id;

    @Column(name = "RATING", precision = 1, scale = 1)
    private BigDecimal rating;

    @Column(name = "RATING_DATE", nullable = false)
    private LocalDate ratingDate;
}