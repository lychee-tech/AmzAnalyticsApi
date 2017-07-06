package com.lychee.amz.analytics.features.account.controller;

import com.lychee.amz.analytics.features.account.AccountController;
import com.lychee.amz.analytics.features.account.dto.CreateAccountRequest;
import com.lychee.amz.analytics.features.account.dto.UserDTO;
import com.lychee.amz.analytics.features.account.dto.UpdateAccountRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;

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
        request.setPhone("5105899616");
        request.setEmail("nie.luyuan@gmail.com");
        request.setLogin("yaoyuannie");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setPassword("123435");

        ResponseEntity<UserDTO> response = restTemplate.postForEntity("/api/accounts",request,UserDTO.class);
        UserDTO user = response.getBody();
        Assert.assertEquals(user.getLastName(), "Nie");
        Assert.assertEquals(user.getEmail(), "nie.luyuan@gmail.com");
    }

    @Test
    public void updateAccountTest(){
        CreateAccountRequest request1 = new CreateAccountRequest();
        request1.setPhone("5105899616");
        request1.setEmail("nie2.luyuan@gmail.com");
        request1.setLogin("yaoyuannie");
        request1.setFirstName("luyuan");
        request1.setLastName("Nie");
        request1.setPassword("123435");

        ResponseEntity<UserDTO> response1 = restTemplate.postForEntity("/api/accounts",request1,UserDTO.class);
        int accountId = response1.getBody().getId();

        UpdateAccountRequest request = new UpdateAccountRequest();
        request.setId(2);
        request.setEmail("nie2.luyuan@gmail.com");
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
        ResponseEntity<UserDTO> response = restTemplate.exchange("/api/accounts/"+ accountId,HttpMethod.PUT,entity,UserDTO.class);
        UserDTO user = response.getBody();
        Assert.assertEquals(user.getLogin(), "yaoyuannie110");
    }
}
