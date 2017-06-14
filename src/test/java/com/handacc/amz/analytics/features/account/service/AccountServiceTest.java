package com.handacc.amz.analytics.features.account.service;

import com.handacc.amz.analytics.features.account.dto.CreateAccountRequest;
import com.handacc.amz.analytics.features.account.dto.CreateAccountResponse;
import com.handacc.amz.analytics.features.account.entity.UserEntity;
import com.handacc.amz.analytics.features.account.model.User;
import com.handacc.amz.analytics.features.account.repo.UserRepo;
import com.handacc.amz.analytics.features.account.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Null;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
    @Autowired
    UserRepo userRepo;

    @Autowired
    AccountService accountService;



    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void createAccountEmailNotNullTest(){
        CreateAccountRequest request = new CreateAccountRequest();
        request.setLogin("yaoyuannie");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setEmail("");
        request.setPassword("P@ssword1");
        try {
            User serviceResponse = accountService.createAccount(request);
        }catch (Exception ex){
            assertTrue(ex instanceof ConstraintViolationException);
            String message = getConstraintViolationsMessage((ConstraintViolationException)ex);
            assertTrue(message.contains("email required"));
        }
    }

    @Test
    public void createAccountEmailFormatIsWrongTest(){
        CreateAccountRequest request = new CreateAccountRequest();
        //test invalid email
        request.setLogin("yaoyuannie");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setEmail("123.com");
        request.setPassword("P@ssword1");
        try {
            User serviceResponse = accountService.createAccount(request);
        }catch (Exception ex){
            assertTrue(ex instanceof ConstraintViolationException);
            String message = getConstraintViolationsMessage((ConstraintViolationException)ex);
            assertTrue(message.contains("invalid email"));
        }
    }

    @Test
    public void createAccountEmailIsCorrectTest(){
        CreateAccountRequest request = new CreateAccountRequest();
        //test invalid email
        request.setLogin("yaoyuannie");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setEmail("nie.luyuan@gmail.com");
        request.setPassword("P@ssword1");
        User serviceResponse = accountService.createAccount(request);
        UserEntity saved = userRepo.findOne(serviceResponse.getId());

        assertEquals(saved.getEmail(),serviceResponse.getEmail());
        assertEquals(saved.getFirstName(),serviceResponse.getFirstName());
        assertEquals(saved.getLastName(),serviceResponse.getLastName());
        assertEquals(saved.getId(),serviceResponse.getId());
        assertEquals(saved.getLogin(),serviceResponse.getLogin());
    }

    @Test
    public void createAccountLoginIsNull(){
        CreateAccountRequest request = new CreateAccountRequest();
        //test invalid email
        request.setLogin("");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setEmail("nie.luyuan@gmail.com");
        request.setPassword("P@ssword1");
        try {
            User serviceResponse = accountService.createAccount(request);
        }catch (Exception ex){
            assertTrue(ex instanceof ConstraintViolationException);
            String message = getConstraintViolationsMessage((ConstraintViolationException)ex);
            assertTrue(message.contains("login name can't be blank"));
        }
    }

    @Test
    public void createAccountLoginExceedSize(){
        CreateAccountRequest request = new CreateAccountRequest();
        //test invalid email
        request.setLogin("dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setEmail("nie.luyuan@gmail.com");
        request.setPassword("P@ssword1");
        try {
            User serviceResponse = accountService.createAccount(request);
        }catch (Exception ex){
            assertTrue(ex instanceof ConstraintViolationException);
            String message = getConstraintViolationsMessage((ConstraintViolationException)ex);
            assertTrue(message.contains("your size is exceed 100"));
        }
    }

    private String getConstraintViolationsMessage(ConstraintViolationException ex){
        List<String> errs = new ArrayList<>();
        Iterator<ConstraintViolation<?>> itr = ex.getConstraintViolations().iterator();

        while(itr.hasNext()){
            ConstraintViolation<?> violation=itr.next();
            errs.add(violation.getMessage());
        }
        return StringUtils.join(errs,";");
    }
}
