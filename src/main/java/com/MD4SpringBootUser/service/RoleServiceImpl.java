package com.MD4SpringBootUser.service;

import com.MD4SpringBootUser.model.Role;
import com.MD4SpringBootUser.repository.IRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService{

    @Autowired
    IRoleRepo iRoleRepo;

    @Override
    public List<Role> findAll() {
        return (List<Role>) iRoleRepo.findAll();
    }
}
