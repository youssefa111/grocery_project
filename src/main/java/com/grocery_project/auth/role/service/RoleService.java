package com.grocery_project.auth.role.service;

import com.grocery_project.auth.role.entity.Role;
import com.grocery_project.auth.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;


    public List<Role> findAll(){
        return repository.findAll();
    }

    public List<Role> insert(List<Role> entity){

        return repository.saveAll(entity);
    }
}
