package com.leechi.amz.analytics.features.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leechi.amz.analytics.features.account.AccountController;
import com.leechi.amz.analytics.features.account.dto.CreateAccountRequest;
import com.leechi.amz.analytics.features.account.dto.AccountResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AccountControllerTest {
    public RestTemplate restTemplate = new RestTemplate();

    @Autowired
    AccountController accountController;

    @Test
    public void CreateAccountTest() throws Exception{
        CreateAccountRequest request = new CreateAccountRequest();
        request.setPhone("5105899616");
        request.setEmail("nie.luyuan@gmail.com");
        request.setLogin("yaoyuannie");
        request.setFirstName("luyuan");
        request.setLastName("Nie");

        ResponseEntity<AccountResponse> response = restTemplate.postForEntity("http://localhost:8080/accounts",request,AccountResponse.class);
        AccountResponse user = response.getBody();
        assertEquals(user.getLastName(),"Nie");
        assertEquals(user.getEmail(),"nie.luyuan@gmail.com");
    };

    @Test
    public void testInvalidEmail() throws Exception{
        CreateAccountRequest request = new CreateAccountRequest();
        request.setPhone("5105899616");
        request.setEmail("nie.luyuangmail.com");
        request.setLogin("yaoyuannie");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        ObjectMapper mapper = new ObjectMapper();

    }


}
