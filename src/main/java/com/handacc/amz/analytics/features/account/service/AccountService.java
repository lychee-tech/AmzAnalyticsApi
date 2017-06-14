package com.handacc.amz.analytics.features.account.service;


import com.handacc.amz.analytics.features.account.dto.CreateAccountRequest;
import com.handacc.amz.analytics.features.account.dto.CreateAccountResponse;
import com.handacc.amz.analytics.features.account.dto.UpdateAccountRequest;
import com.handacc.amz.analytics.features.account.entity.UserEntity;
import com.handacc.amz.analytics.features.account.model.User;
import com.handacc.amz.analytics.features.account.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class AccountService {

    @Autowired
    public UserRepo userRepo;

    public User createAccount(@Valid CreateAccountRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(request.getEmail());
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setLogin(request.getLogin());
        userEntity.setPhone(request.getPhone());
        userEntity.setPassword(request.getPassword());
        userRepo.save(userEntity);

        User user = new User();
        user.setId(userEntity.getId());
        user.setEmail(userEntity.getEmail());
        user.setPhone(userEntity.getPhone());
        user.setPassword(userEntity.getPassword());
        user.setLastName(userEntity.getLastName());
        user.setFirstName(userEntity.getFirstName());
        user.setLogin(userEntity.getLogin());
        return user;
    }

    public User updateAccount(@Valid UpdateAccountRequest request){
        UserEntity userEntity = userRepo.findById(request.getId());
        userEntity.setEmail(request.getEmail());
        userEntity.setLastName(request.getLastName());
        userEntity.setFirstName(request.getFirstName());
        userEntity.setPassword(request.getPassword());
        userEntity.setLogin(request.getLogin());
        userEntity.setPhone(request.getPhone());

        User user = new User();
        user.setEmail(userEntity.getEmail());
        user.setLastName(userEntity.getLastName());
        user.setFirstName(userEntity.getFirstName());
        user.setPassword(userEntity.getPassword());
        user.setLogin(userEntity.getLogin());
        user.setPhone(userEntity.getPhone());

        return user;
    }

}
