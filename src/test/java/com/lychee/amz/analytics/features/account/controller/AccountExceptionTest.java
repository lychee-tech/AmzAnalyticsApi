package com.lychee.amz.analytics.features.account.controller;

import com.lychee.amz.analytics.Exception.ApiErrorResponse;
import com.lychee.amz.analytics.Exception.LycheeFriendlyRunTimeException;
import com.lychee.amz.analytics.Exception.LycheeRunTimeException;
import com.lychee.amz.analytics.features.account.AccountController;
import com.lychee.amz.analytics.features.account.dto.CreateAccountRequest;
import com.lychee.amz.analytics.features.account.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.*;
import static  org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountExceptionTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    AccountController accountController;

    @MockBean
    private AccountService accountService;


    @Test
    public void testRunTimeException(){
        doThrow(new LycheeRunTimeException(200,"xtz","some message")).when(accountService).createAccount(any(CreateAccountRequest.class));

        CreateAccountRequest request = new CreateAccountRequest();
        request.setEmail("nie.luyuan@gmail.com");
        request.setPassword("123435");

        ResponseEntity<ApiErrorResponse> response = restTemplate.postForEntity("/api/accounts", request, ApiErrorResponse.class);
        assertEquals(500, response.getStatusCodeValue());
        ApiErrorResponse error = response.getBody();
        assertEquals("InternalError", error.getCode());
        assertEquals("Internal Error", error.getMessage());
    }

    @Test
    public void testFriendlyRunTimeException(){
        doThrow(new LycheeFriendlyRunTimeException()).when(accountService).createAccount(any(CreateAccountRequest.class));

        CreateAccountRequest request = new CreateAccountRequest();
        request.setEmail("nie.luyuan@gmail.com");
        request.setPassword("123435");

        ResponseEntity<ApiErrorResponse> response = restTemplate.postForEntity("/api/accounts", request, ApiErrorResponse.class);
        assertEquals(403, response.getStatusCodeValue());
        ApiErrorResponse error = response.getBody();
        assertEquals("Forbidden", error.getCode());
        assertEquals("user did not has permission to execute the action", error.getMessage());
    }


}
