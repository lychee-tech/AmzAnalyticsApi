package com.leechi.amz.analytics.features.account;


import com.leechi.amz.analytics.features.account.dto.CreateAccountRequest;
import com.leechi.amz.analytics.features.account.dto.AccountResponse;
import com.leechi.amz.analytics.features.account.dto.UpdateAccountRequest;
import com.leechi.amz.analytics.features.account.model.User;
import com.leechi.amz.analytics.features.account.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/accounts", method= RequestMethod.POST)
    public AccountResponse createAccount(@RequestBody CreateAccountRequest request) {
        User user = accountService.createAccount(request);
        AccountResponse response = new AccountResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setPassword(user.getPassword());
        response.setLastName(user.getLastName());
        response.setFirstName(user.getFirstName());
        response.setLogin(user.getLogin());
        return response;
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.PUT)
    public AccountResponse updateAccount(@PathVariable String id, @RequestBody UpdateAccountRequest request){
        AccountResponse response = new AccountResponse();
        User user = accountService.updateAccount(Integer.parseInt(id),request);

        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setPassword(user.getPassword());
        response.setLastName(user.getLastName());
        response.setFirstName(user.getFirstName());
        response.setLogin(user.getLogin());
        return response;
    }


}
