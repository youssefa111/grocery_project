package com.grocery_project.auth.role.repository;

import com.grocery_project.auth.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Short> {
}
