package com.lychee.amz.analytics.features.login;

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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LoginControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCannotFoundUser(){
        HttpEntity<Object> entity = new HttpEntity<>(HttpBasicHelp.createHttpBasicHeader("user","user"));
        ResponseEntity<String> response = restTemplate.exchange("/login", HttpMethod.POST, entity, String.class);
        String body = response.getBody();
    }
}
