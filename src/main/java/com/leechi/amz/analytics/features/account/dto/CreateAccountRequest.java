package com.leechi.amz.analytics.features.account.dto;


import com.leechi.amz.analytics.features.account.validate.Phone;
import com.leechi.amz.analytics.features.account.validate.UniqueEmail;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class CreateAccountRequest extends UpdateAccountRequest {
    @Email(message = "invalid email")
    @NotBlank(message = "email cannot be empty")
    @Size(max = 100, message = "you have reach max limit")
    @UniqueEmail
    private String email;

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
