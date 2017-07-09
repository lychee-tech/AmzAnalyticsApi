package com.lychee.amz.analytics.features.account.controller;

import com.lychee.amz.analytics.features.account.AccountController;
import com.lychee.amz.analytics.features.account.dto.CreateAccountRequest;
import com.lychee.amz.analytics.features.account.dto.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    AccountController accountController;

    @Test
    public void CreateAccountTest() throws Exception{
        CreateAccountRequest request = new CreateAccountRequest();
        request.setEmail("nie.luyuan@gmail.com");

        request.setPassword("123435");

        ResponseEntity<UserDTO> response = restTemplate.postForEntity("/api/accounts",request,UserDTO.class);
        UserDTO user = response.getBody();
        Assert.assertEquals(user.getEmail(), "nie.luyuan@gmail.com");
    }


}
