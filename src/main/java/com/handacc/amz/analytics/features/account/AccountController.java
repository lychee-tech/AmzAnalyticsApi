package com.handacc.amz.analytics.features.account;


import com.handacc.amz.analytics.features.account.dto.CreateAccountRequest;
import com.handacc.amz.analytics.features.account.dto.CreateAccountResponse;
import com.handacc.amz.analytics.features.account.dto.UpdateAccountRequest;
import com.handacc.amz.analytics.features.account.dto.UpdateAccountResponse;
import com.handacc.amz.analytics.features.account.entity.UserEntity;
import com.handacc.amz.analytics.features.account.model.User;
import com.handacc.amz.analytics.features.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/accounts", method= RequestMethod.POST)
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request) {
        User user = accountService.createAccount(request);
        CreateAccountResponse response = new CreateAccountResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setPassword(user.getPassword());
        response.setLastName(user.getLastName());
        response.setFirstName(user.getFirstName());
        response.setLogin(user.getLogin());
        return response;
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.PUT)
    public UpdateAccountResponse updateAccount(@RequestBody UpdateAccountRequest request){
        UpdateAccountResponse response = new UpdateAccountResponse();
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
