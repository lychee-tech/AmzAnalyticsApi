package com.lychee.amz.analytics.features.account;


import com.lychee.amz.analytics.features.account.dto.CreateAccountRequest;
import com.lychee.amz.analytics.features.account.dto.UserDTO;
import com.lychee.amz.analytics.features.account.dto.UpdateAccountRequest;
import com.lychee.amz.analytics.features.account.model.User;
import com.lychee.amz.analytics.features.account.service.AccountService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AccountController {
    private static Logger logger= LoggerFactory.getLogger(AccountController.class);


    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/api/accounts", method= RequestMethod.POST)
    public UserDTO createAccount(@RequestBody CreateAccountRequest request) {
        logger.info(String.format("received to create account with login: %s and email: %s",
                request.getLogin(), request.getEmail()));

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

    @RequestMapping(value = "/api/accounts/{id}", method = RequestMethod.PUT)
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
