package com.leechi.amz.analytics.features.auth;

import com.leechi.amz.analytics.features.account.dto.AccountResponse;
import com.leechi.amz.analytics.features.account.dto.CreateAccountRequest;
import com.leechi.amz.analytics.features.account.dto.UpdateAccountRequest;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

import static org.junit.Assert.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SecurityRequestTest {
    public RestTemplate restTemplate = new RestTemplate();

    @Test
    public void requestWithoutAuth(){
        try{
            ResponseEntity response = restTemplate.getForEntity("http://localhost:8080/",String.class);
        }catch (Exception ex){
            assertTrue(ex instanceof HttpClientErrorException);
            assertTrue(ex.getMessage().contains("401 null"));
        }

    }

    @Test
    public void requestCreateAccountNoNeedAuth(){
        CreateAccountRequest request = new CreateAccountRequest();
        request.setPhone("5105899616");
        request.setEmail("nie.luyuan@gmail.com");
        request.setLogin("yaoyuannie");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setPassword("123435");

        ResponseEntity<AccountResponse> response = restTemplate.postForEntity("http://localhost:8080/accounts",request, AccountResponse.class);
        assertEquals(response.getStatusCode().toString(),"200");
    }

    @Test
    public void requestUpdateAccountNeedAuth(){
        CreateAccountRequest request1 = new CreateAccountRequest();
        request1.setPhone("5105899616");
        request1.setEmail("nie.luyuan@gmail.com");
        request1.setLogin("yaoyuannie");
        request1.setFirstName("luyuan");
        request1.setLastName("Nie");
        request1.setPassword("123435");

        ResponseEntity<AccountResponse> response1 = restTemplate.postForEntity("http://localhost:8080/accounts",request1,AccountResponse.class);

        UpdateAccountRequest request = new UpdateAccountRequest();
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
        ResponseEntity<AccountResponse> response = restTemplate.exchange("http://localhost:8080/accounts/1", HttpMethod.PUT,entity,AccountResponse.class);
        assertEquals(response.getStatusCode().toString(),"200");
    }

}
