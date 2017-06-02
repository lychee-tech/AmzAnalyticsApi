package com.handacc.amz.analytics.features.account.service;


import com.handacc.amz.analytics.features.account.dto.CreateAccountRequest;
import com.handacc.amz.analytics.features.account.entity.UserEntity;
import com.handacc.amz.analytics.features.account.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
public class AccountService {

    @Autowired
    public UserRepo userRepo;

    public UserEntity createAccount(@Validated CreateAccountRequest request) {
        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setLogin(request.getLogin());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());
        userRepo.save(user);
        return user;
    }

}
