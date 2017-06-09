package com.handacc.amz.analytics.features.account.service;

import com.handacc.amz.analytics.features.account.dto.CreateAccountRequest;
import com.handacc.amz.analytics.features.account.dto.CreateAccountResponse;
import com.handacc.amz.analytics.features.account.entity.UserEntity;
import com.handacc.amz.analytics.features.account.model.User;
import com.handacc.amz.analytics.features.account.repo.UserRepo;
import com.handacc.amz.analytics.features.account.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
    @Autowired
    UserRepo userRepo;

    @Autowired
    AccountService accountService;

    @Test
    public void createAccountTest(){
        User user = new User();
        user.setLogin("yaoyuannie");
        user.setFirstName("luyuan");
        user.setLastName("Nie");
        user.setEmail("nie.luyuan@gmail.com");
        user.setPassword("P@ssword1");
        CreateAccountResponse serviceResponse = accountService.createAccount(user);
        CreateAccountResponse response = new CreateAccountResponse();
        response.setLogin("yaoyuannie");
        response.setFirstName("luyuan");
        response.setLastName("Nie");
        response.setEmail("nie.luyuan@gmail.com");
        response.setPassword("P@ssword1");
        response.setId(1);
        assertEquals(serviceResponse.getEmail(),response.getEmail());

    }
}
