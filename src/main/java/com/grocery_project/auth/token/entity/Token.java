package com.grocery_project.auth.token.entity;

import com.grocery_project.auth.user.entity.User;
import com.grocery_project.core.constant.TokenType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String token;

    @Column(name = "expire_date")
    private Date expireDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private TokenType tokenType;

    private boolean expired;

    private boolean revoked;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
