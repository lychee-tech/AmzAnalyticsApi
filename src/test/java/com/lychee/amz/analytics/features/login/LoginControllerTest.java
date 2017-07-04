package com.lychee.amz.analytics.features.login;

import com.lychee.amz.analytics.Exception.ApiErrorResponse;
import com.lychee.amz.analytics.testhelp.HttpBasicHelp;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LoginControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCannotFoundUser(){
        HttpEntity<Object> entity = new HttpEntity<>(HttpBasicHelp.createHttpBasicHeader("user","user"));
        ResponseEntity<ApiErrorResponse> response = restTemplate.exchange("/login", HttpMethod.POST, entity, ApiErrorResponse.class);
        assertEquals(401, response.getStatusCodeValue());
        ApiErrorResponse apiErrorResponse = response.getBody();
        assertEquals("AuthenticationError", apiErrorResponse.getCode());
    }
}
