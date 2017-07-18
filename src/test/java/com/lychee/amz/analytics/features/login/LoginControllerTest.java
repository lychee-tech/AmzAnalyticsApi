package com.lychee.amz.analytics.features.login;

import com.lychee.amz.analytics.Exception.ApiErrorResponse;
import com.lychee.amz.analytics.features.account.entity.UserEntity;
import com.lychee.amz.analytics.features.account.repo.UserRepo;
import com.lychee.amz.analytics.testhelp.HttpCredentialHelp;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LoginControllerTest {
    @MockBean
    private UserRepo userRepo;
    @Autowired
    private TestRestTemplate restTemplate;



    @Test
    public void testCannotFoundUser(){
        doReturn(null).when(userRepo).findByEmail(anyString());

        HttpEntity<Object> entity = new HttpEntity<>(HttpCredentialHelp.createHttpBasicHeader("user", "user"));
        ResponseEntity<ApiErrorResponse> response = restTemplate.exchange("/login", HttpMethod.POST, entity, ApiErrorResponse.class);
        assertEquals(401, response.getStatusCodeValue());
        ApiErrorResponse apiErrorResponse = response.getBody();
        assertEquals("AuthenticationError", apiErrorResponse.getCode());
        assertEquals("Authentication error, make sure login name and password are correct.", apiErrorResponse.getMessage());
    }

    @Test
    public void testHttpBasicInBadFormat(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic abc");

        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<ApiErrorResponse> response = restTemplate.exchange("/login", HttpMethod.POST, entity, ApiErrorResponse.class);
        assertEquals(401, response.getStatusCodeValue());
        ApiErrorResponse apiErrorResponse = response.getBody();
        assertEquals("AuthenticationError", apiErrorResponse.getCode());
        assertEquals("Bad credential.", apiErrorResponse.getMessage());
    }

    @Test
    public void testLoginSuccess(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setEmail("wenhao.lin@gmail.com");
        userEntity.setEncryptedPassword(encoder.encode("user"));

        doReturn(userEntity).when(userRepo).findByEmail(anyString());

        HttpEntity<Object> entity = new HttpEntity<>(HttpCredentialHelp.createHttpBasicHeader("wenhao.lin@gmail.com", "user"));
        ResponseEntity<AuthUserDTO> response = restTemplate.exchange("/login", HttpMethod.POST, entity, AuthUserDTO.class);
        assertEquals(201, response.getStatusCodeValue());
        String token = response.getHeaders().get("Authorization").get(0);
        AuthUserDTO authUser=response.getBody();
        assertNotNull(authUser);
        assertTrue(token.startsWith("Bearer"));
    }


    @Test
    public void testJwtValidationAfterLogin(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setEmail("wenhao.lin@gmail.com");
        userEntity.setEncryptedPassword(encoder.encode("user"));

        doReturn(userEntity).when(userRepo).findByEmail(anyString());

        HttpEntity<Object> entity = new HttpEntity<>(HttpCredentialHelp.createHttpBasicHeader("wenhao.lin@gmail.com", "user"));
        ResponseEntity<Void> response = restTemplate.exchange("/login", HttpMethod.POST, entity, Void.class);
        String bearer = response.getHeaders().get("Authorization").get(0);
        assertTrue(bearer.startsWith("Bearer"));
        String token = bearer.substring(6);


        HttpEntity<Object> request2 = new HttpEntity<>(HttpCredentialHelp.createHttpBearerHeader(token));
        ResponseEntity<String> response2 = restTemplate.exchange("/api/hello", HttpMethod.GET, request2, String.class);

        assertEquals("success", response2.getBody());
    }


    @Test
    public void testInvalidJwtToken(){
        HttpEntity<Object> request = new HttpEntity<>(HttpCredentialHelp.createHttpBearerHeader("abc"));
        ResponseEntity<ApiErrorResponse> response = restTemplate.exchange("/api/hello", HttpMethod.GET, request, ApiErrorResponse.class);
        ApiErrorResponse errorResponse = response.getBody();
        assertEquals("AuthenticationError", errorResponse.getCode());
    }
}
