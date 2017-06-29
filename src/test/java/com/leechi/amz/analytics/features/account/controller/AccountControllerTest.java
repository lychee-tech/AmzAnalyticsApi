package com.leechi.amz.analytics.features.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leechi.amz.analytics.features.account.AccountController;
import com.leechi.amz.analytics.features.account.dto.CreateAccountRequest;
import com.leechi.amz.analytics.features.account.dto.AccountResponse;
import com.leechi.amz.analytics.features.account.dto.UpdateAccountRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import org.apache.tomcat.util.codec.binary.Base64;
import sun.net.www.protocol.http.HttpAuthenticator;

import java.nio.charset.Charset;

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
        request.setPassword("123435");

        ResponseEntity<AccountResponse> response = restTemplate.postForEntity("http://localhost:8080/accounts",request,AccountResponse.class);
        AccountResponse user = response.getBody();
        assertEquals(user.getLastName(),"Nie");
        assertEquals(user.getEmail(),"nie.luyuan@gmail.com");
    };

    @Test
    public void updateAccountTest(){
        CreateAccountRequest request1 = new CreateAccountRequest();
        request1.setPhone("5105899616");
        request1.setEmail("nie.luyuan@gmail.com");
        request1.setLogin("yaoyuannie");
        request1.setFirstName("luyuan");
        request1.setLastName("Nie");
        request1.setPassword("123435");

        ResponseEntity<AccountResponse> response1 = restTemplate.postForEntity("http://localhost:8080/accounts",request1,AccountResponse.class);

        UpdateAccountRequest request = new UpdateAccountRequest();
        request.setId(2);
        request.setEmail("nie.luyuan@gmail.com");
        request.setLogin("yaoyuannie110");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setPassword("123435");

        HttpHeaders headers = new HttpHeaders();
        String auth = "yaoyuannie"+":"+"123435";
        byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodeAuth);
        headers.set("Authorization",authHeader);

        HttpEntity entity = new HttpEntity(request,headers);
        ResponseEntity<AccountResponse> response = restTemplate.exchange("http://localhost:8080/accounts/2",HttpMethod.PUT,entity,AccountResponse.class);
        AccountResponse user = response.getBody();
        assertEquals(user.getLogin(),"yaoyuannie110");
    }
}
