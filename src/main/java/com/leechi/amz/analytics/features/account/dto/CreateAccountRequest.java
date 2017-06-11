package com.leechi.amz.analytics.features.account.dto;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class CreateAccountRequest {
    @NotBlank(message="login name can't be blank")
    @Size(max=100, message="")
    private String login;
    private String firstName;
    private String lastName;
    @Email(message="invalid email")
    @Size(max=100, message="")
    private String email;


    private String phone;


    private String password;

}
