package com.lychee.amz.analytics.features.account.entity;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Date;

@Entity(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String encryptedPassword;

    private String firstName;
    private String lastName;

    private Date deleteDate;

    /**
     *
     * @return user id
     */
    public Integer getId() {
        return id;
    }

    /**
     * set user id
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }


    /**
     *
     *
     * @return user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * set user email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }



    public String getEncryptedPassword() {
        return encryptedPassword;
    }


    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
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


    public String getDisplayName(){
        if (!StringUtils.isBlank(firstName)) {
            return firstName;
        } else {
            return email.split("@")[0];
        }
    }
}
