package com.MD4SpringBootUser.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Validated
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "account.blank")
    private String account;

    @NotBlank(message = "password.blank")
    private String password;

    @NotBlank(message = "fullName.blank")
    private String fullName;

    @NotBlank(message = "phone.blank")
    private String phone;

    @NotBlank(message = "email.blank")
    private String email;

    private String avatar;

    @ManyToOne
    private Role role;
}
