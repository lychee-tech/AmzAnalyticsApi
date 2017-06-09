package com.handacc.amz.analytics.features.account.service;


import com.handacc.amz.analytics.features.account.dto.CreateAccountRequest;
import com.handacc.amz.analytics.features.account.dto.CreateAccountResponse;
import com.handacc.amz.analytics.features.account.entity.UserEntity;
import com.handacc.amz.analytics.features.account.model.User;
import com.handacc.amz.analytics.features.account.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
public class AccountService {

    @Autowired
    public UserRepo userRepo;

    public CreateAccountResponse createAccount(@Valid User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setLogin(user.getLogin());
        userEntity.setPhone(user.getPhone());
        userEntity.setPassword(user.getPassword());
        userRepo.save(userEntity);

        CreateAccountResponse accountResponse = new CreateAccountResponse();
        accountResponse.setId(userEntity.getId());
        accountResponse.setEmail(user.getEmail());
        accountResponse.setPhone(user.getPhone());
        accountResponse.setPassword(user.getPassword());
        accountResponse.setLastName(user.getLastName());
        accountResponse.setFirstName(user.getFirstName());
        accountResponse.setLogin(user.getLogin());
        return accountResponse;
    }

}
