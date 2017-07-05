package com.lychee.amz.analytics.features.authentication;

import com.lychee.amz.analytics.features.account.dto.UserDTO;
import com.lychee.amz.analytics.features.account.dto.CreateAccountRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AuthenticationTest {
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void requestCreateAccountNoNeedAuth(){
        CreateAccountRequest request = new CreateAccountRequest();
        request.setPhone("5105899616");
        request.setEmail("nie.luyuan@gmail.com");
        request.setLogin("yaoyuannie");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setPassword("123435");

        ResponseEntity<UserDTO> response = restTemplate.postForEntity("/api/accounts",request, UserDTO.class);
        assertEquals(200, response.getStatusCode().value());
    }
}
