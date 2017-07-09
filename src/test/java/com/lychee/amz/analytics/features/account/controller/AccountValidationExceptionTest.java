package com.lychee.amz.analytics.features.account.controller;


import com.lychee.amz.analytics.Exception.ApiErrorResponse;
import com.lychee.amz.analytics.features.account.AccountController;
import com.lychee.amz.analytics.features.account.dto.CreateAccountRequest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountValidationExceptionTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    AccountController accountController;


    @Test
    public void testDuplicateUser(){
        CreateAccountRequest request = new CreateAccountRequest();
        request.setEmail("wenhaodup.lin@gmail.com");
        request.setPassword("123435");
        restTemplate.postForEntity("/api/accounts", request, ApiErrorResponse.class);

        ResponseEntity<ApiErrorResponse> response = restTemplate.postForEntity("/api/accounts", request, ApiErrorResponse.class);
        assertEquals(400, response.getStatusCodeValue());
        ApiErrorResponse error = response.getBody();
        assertEquals("InvalidInput", error.getCode());
        assertEquals("email: Email has already registered.", error.getMessage());
    }
}
