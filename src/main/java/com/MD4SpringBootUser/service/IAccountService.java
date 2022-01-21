package com.MD4SpringBootUser.service;

import com.MD4SpringBootUser.model.Account;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();

    void save (Account account);

    Account findById (Long id);

    void delete (Long id);

    List<Account> search(String name);
}
