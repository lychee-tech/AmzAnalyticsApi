package com.leechi.amz.analytics.features.account;


import com.leechi.amz.analytics.features.account.dto.CreateAccountRequest;
import com.leechi.amz.analytics.features.account.dto.CreateAccountResponse;
import com.leechi.amz.analytics.features.account.entity.UserEntity;
import com.leechi.amz.analytics.features.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/accounts", method= RequestMethod.POST)
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request) {
        UserEntity user = accountService.createAccount(request);

        return null;
    }

}
