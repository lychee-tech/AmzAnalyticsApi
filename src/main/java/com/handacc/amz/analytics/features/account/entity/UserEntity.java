package com.handacc.amz.analytics.features.account.entity;

import javax.persistence.*;

@Entity(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;

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
     * @return user login name
     */
    public String getLogin() {
        return login;
    }

    /**
     * set user login name
     *
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     *
     *
     * @return user first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * set user first name
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     *
     * @return user last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * set user last name
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    /**
     *
     *
     * @return user phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * set user phone
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     *
     * @return  user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * set user password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
