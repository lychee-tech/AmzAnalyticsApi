package com.lychee.amz.analytics.features.account.dto;


import com.lychee.amz.analytics.features.account.validate.UniqueEmail;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@UniqueEmail(propertyPath = "email")
public class CreateAccountRequest {

    @Email(message = "invalid email")
    @NotBlank(message = "email cannot be empty")
    @Size(max = 100, message = "you have reach max limit")
    private String email;


    @NotBlank(message = "password cannot be empty")
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
