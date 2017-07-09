package com.lychee.amz.analytics.features.account.service;


import com.lychee.amz.analytics.features.account.dto.CreateAccountRequest;
import com.lychee.amz.analytics.features.account.entity.UserEntity;
import com.lychee.amz.analytics.features.account.model.User;
import com.lychee.amz.analytics.features.account.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class AccountService {

    @Autowired
    public UserRepo userRepo;

   @Autowired private PasswordEncoder passwordEncoder;

    public User createAccount(@Valid CreateAccountRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(request.getEmail());
        userEntity.setEncryptedPassword(passwordEncoder.encode(request.getPassword()));
        userRepo.save(userEntity);

        User user = new User();
        user.setId(userEntity.getId());
        user.setEmail(userEntity.getEmail());
        return user;
    }



}
