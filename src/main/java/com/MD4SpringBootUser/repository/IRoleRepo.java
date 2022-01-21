package com.MD4SpringBootUser.repository;

import com.MD4SpringBootUser.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepo extends CrudRepository<Role, Long> {
}
