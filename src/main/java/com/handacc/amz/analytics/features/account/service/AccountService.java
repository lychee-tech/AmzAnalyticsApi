package com.handacc.amz.analytics.features.account.service;


import com.handacc.amz.analytics.features.account.dto.CreateAccountRequest;
import com.handacc.amz.analytics.features.account.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
public class AccountService {

    public UserEntity createAccount(@Validated CreateAccountRequest request) {
        return null;
    }

}
