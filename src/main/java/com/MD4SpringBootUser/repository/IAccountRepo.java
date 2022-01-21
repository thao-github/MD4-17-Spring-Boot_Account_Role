package com.MD4SpringBootUser.repository;

import com.MD4SpringBootUser.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IAccountRepo extends PagingAndSortingRepository<Account, Long> {
    @Query(value = "select a from Account a where a.fullName like concat('%',:fullName, '%')")
    ArrayList<Account> findAllByName (@Param("fullName")String name);
}
