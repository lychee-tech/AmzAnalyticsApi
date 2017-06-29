package com.leechi.amz.analytics.features.account.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leechi.amz.analytics.features.account.validate.UniqueEmail;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Transient;
import javax.validation.constraints.Size;

@UniqueEmail
public class UpdateAccountRequest {
    @JsonIgnore
    private Integer id;

    @NotBlank(message = "login cannot be empty")
    @Size(max=100,message = "login reach max character limit")
    private String login;
    private String firstName;
    private String lastName;

    @Email(message = "invalid email")
    @NotBlank(message = "email cannot be empty")
    @Size(max = 100, message = "you have reach max limit")
    private String email;
    private String phone;

    @NotBlank(message = "password cannot be empty")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
