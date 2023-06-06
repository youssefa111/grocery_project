package com.grocery_project.auth.user.entity;


import com.grocery_project.auth.role.entity.Role;
import com.grocery_project.auth.token.entity.Token;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@DynamicInsert
@DynamicUpdate
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name",length = 30, nullable = false)
    private String firstName;
    @Column(name = "last_name",length = 30 , nullable = false)
    private String lastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "join_date", nullable = false)
    private LocalDate  joinDate;
    @Column(length = 50 , nullable = false)
    private String address;
    @Column(length = 11 , nullable = false)
    private String phone;
    @Column(length = 30, nullable = false)
    private String username;
    @Email
    @Column(length = 50, nullable = false)
    private String email;
    @Column( nullable = false)
    private String password;
    @Column(name = "is_blocked")
    private short isBlocked ;
    @Column(name = "is_active")
    private short isActive;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(role != null)
        return List.of(new SimpleGrantedAuthority(role.getRoleType().name()));
        else
            return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
