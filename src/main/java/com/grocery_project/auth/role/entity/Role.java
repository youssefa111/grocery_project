package com.grocery_project.auth.role.entity;

import com.grocery_project.core.constant.RoleType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Short id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type",length = 30)
    private RoleType roleType;

//    @OneToOne(mappedBy = "role",fetch = FetchType.LAZY)
//    private User user;



}
