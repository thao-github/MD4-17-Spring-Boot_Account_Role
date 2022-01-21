package com.MD4SpringBootUser.controller;

import com.MD4SpringBootUser.model.Account;
import com.MD4SpringBootUser.model.Role;
import com.MD4SpringBootUser.service.IAccountService;
import com.MD4SpringBootUser.service.IRoleService;
import com.MD4SpringBootUser.validate.AccountEmailDuplicateValidate;
import com.MD4SpringBootUser.validate.OtherAccountDuplicateValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    IAccountService accountService;

    @Autowired
    IRoleService roleService;

    @Autowired
    AccountEmailDuplicateValidate accountEmailDuplicateValidate;

    @Autowired
    OtherAccountDuplicateValidate otherAccountDuplicateValidate;

    @ModelAttribute("account")
    public Account account() {
        return new Account();
    }

    @ModelAttribute("roles")
    public List<Role> roles() {
        return roleService.findAll();
    }

    @ModelAttribute("accounts")
    public List<Account> accounts() {
        return accountService.findAll();
    }

    @GetMapping("/accounts")
    public ModelAndView list() {
        return new ModelAndView("account/list");
    }

    @GetMapping("/create")
    public String createForm() {
        return "account/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute Account account, BindingResult bindingResult, @RequestParam MultipartFile avatarUpload) {
        accountEmailDuplicateValidate.validate(account, bindingResult);
        if (bindingResult.hasErrors()) {
            return "account/create";
        }
        String fileName = avatarUpload.getOriginalFilename();
        try {
            FileCopyUtils.copy(avatarUpload.getBytes(), new File("/Users/thaojuice/Desktop/Workspace/java-codegym/[MD4] Spring MVC/[2] Project/[17][18] Spring Boot/User-Role/src/main/resources/static/img/" + fileName));
            String urlAvatar = "/img/" + fileName;
            account.setAvatar(urlAvatar);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        accountService.save(account);
        return "redirect:/accounts";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        Account account = accountService.findById(id);
        ModelAndView modelAndView = new ModelAndView("account/edit");
        modelAndView.addObject("account", account);
        return modelAndView;
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(value = "account") Account account, BindingResult bindingResult, @RequestParam MultipartFile avatarUpload) {
        otherAccountDuplicateValidate.validate(account, bindingResult);
        if (bindingResult.hasErrors()) {
            return "account/edit";
        }
        String fileName = avatarUpload.getOriginalFilename();
        try {
            FileCopyUtils.copy(avatarUpload.getBytes(), new File("/Users/thaojuice/Desktop/Workspace/java-codegym/[MD4] Spring MVC/[2] Project/[17][18] Spring Boot/User-Role/src/main/resources/static/img/" + fileName));
            String urlAvatar = "/img/" + fileName;
            account.setAvatar(urlAvatar);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        accountService.save(account);
        return "redirect:/accounts";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/account/delete");
        modelAndView.addObject("account", accountService.findById(id));
        return modelAndView;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(value = "id") Long id) {
        accountService.delete(id);
        return "redirect:/accounts";
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam(value = "search") String search) {
        ModelAndView modelAndView = new ModelAndView("/account/search");
        modelAndView.addObject("searchResult", accountService.search(search));
        return modelAndView;
    }
}
