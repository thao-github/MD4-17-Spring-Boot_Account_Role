package com.MD4SpringBootUser.validate;

import com.MD4SpringBootUser.model.Account;
import com.MD4SpringBootUser.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class OtherAccountDuplicateValidate implements Validator {

    @Autowired
    IAccountService iAccountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Account account = (Account) target;
        List<Account> accounts = iAccountService.findAll();
        for (Account a : accounts) {
            if (!account.getId().equals(a.getId()) && a.getAccount().equals(account.getAccount())) {
                errors.rejectValue("account", "account.duplicate", "account.duplicate");
            }
            if (!account.getId().equals(a.getId()) && a.getEmail().equals(account.getEmail())) {
                errors.rejectValue("email", "email.duplicate", "email.duplicate");
            }
        }
    }
}
