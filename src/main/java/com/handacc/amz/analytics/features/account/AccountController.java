package com.handacc.amz.analytics.features.account;


import com.handacc.amz.analytics.features.account.dto.CreateAccountRequest;
import com.handacc.amz.analytics.features.account.dto.CreateAccountResponse;
import com.handacc.amz.analytics.features.account.entity.UserEntity;
import com.handacc.amz.analytics.features.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @CrossOrigin
    @RequestMapping(value = "/accounts", method= RequestMethod.POST)
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request) {
        UserEntity user = accountService.createAccount(request);
        CreateAccountResponse accountResponse = new CreateAccountResponse();
        accountResponse.setId(user.getId());
        accountResponse.setEmail(request.getEmail());
        accountResponse.setPhone(request.getPhone());
        accountResponse.setPassword(request.getPassword());
        accountResponse.setLastName(request.getLastName());
        accountResponse.setFirstName(request.getFirstName());
        accountResponse.setLogin(request.getLogin());
        return accountResponse;
    }

}
