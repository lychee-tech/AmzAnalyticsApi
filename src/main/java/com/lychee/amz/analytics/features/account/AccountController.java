package com.lychee.amz.analytics.features.account;


import com.lychee.amz.analytics.features.account.dto.CreateAccountRequest;
import com.lychee.amz.analytics.features.account.dto.UserDTO;
import com.lychee.amz.analytics.features.account.dto.UpdateAccountRequest;
import com.lychee.amz.analytics.features.account.model.User;
import com.lychee.amz.analytics.features.account.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/accounts", method= RequestMethod.POST)
    public UserDTO createAccount(@RequestBody CreateAccountRequest request) {
        User user = accountService.createAccount(request);
        UserDTO response = new UserDTO();
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
    public UserDTO updateAccount(@PathVariable String id, @RequestBody UpdateAccountRequest request){
        UserDTO response = new UserDTO();
        request.setId(Integer.parseInt(id));
        User user = accountService.updateAccount(request);

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
