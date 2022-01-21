package com.MD4SpringBootUser.service;

import com.MD4SpringBootUser.model.Account;
import com.MD4SpringBootUser.repository.IAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService{

    @Autowired
    IAccountRepo iAccountRepo;

    @Override
    public List<Account> findAll() {
        return (List<Account>) iAccountRepo.findAll();
    }

    @Override
    public void save(Account account) {
    iAccountRepo.save(account);
    }

    @Override
    public Account findById(Long id) {
        return iAccountRepo.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        iAccountRepo.deleteById(id);
    }

    @Override
    public List<Account> search(String name) {
        return iAccountRepo.findAllByName(name);
    }
}
