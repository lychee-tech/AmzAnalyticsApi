package com.handacc.amz.analytics.features.account;


import com.handacc.amz.analytics.features.account.dto.CreateAccountRequest;
import com.handacc.amz.analytics.features.account.dto.CreateAccountResponse;
import com.handacc.amz.analytics.features.account.entity.UserEntity;
import com.handacc.amz.analytics.features.account.model.User;
import com.handacc.amz.analytics.features.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @CrossOrigin
    @RequestMapping(value = "/accounts", method= RequestMethod.POST)
    public CreateAccountResponse createAccount(@Validated @RequestBody CreateAccountRequest request) {
        User user = new User();
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());
        user.setEmail(request.getEmail());
        user.setLogin(request.getLogin());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());

        CreateAccountResponse response = accountService.createAccount(user);

        return response;
    }



}
