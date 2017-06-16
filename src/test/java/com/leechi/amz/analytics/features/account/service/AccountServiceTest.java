package com.leechi.amz.analytics.features.account.service;

import com.leechi.amz.analytics.features.account.dto.CreateAccountRequest;
import com.leechi.amz.analytics.features.account.dto.UpdateAccountRequest;
import com.leechi.amz.analytics.features.account.entity.UserEntity;
import com.leechi.amz.analytics.features.account.model.User;
import com.leechi.amz.analytics.features.account.repo.UserRepo;
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    @Test
    public void AccountPhoneValidateTest(){
        CreateAccountRequest request = new CreateAccountRequest();
        //test invalid email
        request.setLogin("yaoyuannie");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setEmail("nie.luyuan@gmail.com");
        request.setPassword("P@ssword1");
        request.setPhone("3dfsf3");
        try{
            User serviceResponse = accountService.createAccount(request);
        }catch (Exception ex){
            assertTrue(ex instanceof ConstraintViolationException);
            String message = getConstraintViolationsMessage((ConstraintViolationException)ex);
            assertTrue(message.contains("phone"));
        }
    }

    @Test
    public void CreateAccountUnqueEmailTest(){
        CreateAccountRequest request = new CreateAccountRequest();
        //test invalid email
        request.setLogin("yaoyuannie");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setEmail("nie.luyuan@gmail.com");
        request.setPassword("P@ssword1");
        request.setPhone("3dfsf3");
        User serviceResponse = accountService.createAccount(request);
        try{
            User serviceResponse2 = accountService.createAccount(request);
        }catch (Exception ex){
            assertTrue(ex instanceof ConstraintViolationException);
            String message = getConstraintViolationsMessage((ConstraintViolationException)ex);
            assertTrue(message.contains("Email has already Registered"));
        }
    }

    @Test
    public void UpdateAccountSuccessTest(){
        UserEntity user = new UserEntity();
        user.setLogin("yaoyuannie");
        user.setFirstName("luyuan");
        user.setLastName("Nie");
        user.setEmail("nie.luyuan@gmail.com");
        user.setPassword("P@ssword1");
        user.setPhone("3dfsf3");
        userRepo.save(user);

        UpdateAccountRequest request = new UpdateAccountRequest();
        request.setLogin("yaoyuannie110");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setEmail("nie.luyuan@gmail.com");
        request.setPassword("P@ssword1");
        request.setPhone("3dfsf3");

        User updateUser = accountService.updateAccount(1, request);
        UserEntity saved = userRepo.findById(1);
        assertEquals(updateUser.getLogin(),saved.getLogin());
    }

    @Test
    public void UpdateAccountEmailUniqueTest(){
        UserEntity user1 = new UserEntity();
        user1.setLogin("yaoyuannie");
        user1.setFirstName("luyuan");
        user1.setLastName("Nie");
        user1.setEmail("nie.luyuan@gmail.com");
        user1.setPassword("P@ssword1");
        user1.setPhone("3dfsf3");
        userRepo.save(user1);

        UserEntity user2 = new UserEntity();
        user2.setLogin("yaoyuannie");
        user2.setFirstName("luyuan");
        user2.setLastName("Nie");
        user2.setEmail("test@gmail.com");
        user2.setPassword("P@ssword1");
        user2.setPhone("3dfsf3");
        userRepo.save(user2);

        UpdateAccountRequest request = new UpdateAccountRequest();
        request.setLogin("yaoyuannie110");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setEmail("test@gmail.com");
        request.setPassword("P@ssword1");
        request.setPhone("3dfsf3");
        try {
            User updateUser = accountService.updateAccount(1, request);
            UserEntity saved = userRepo.findById(1);
            System.out.print("hello");
        }catch (Exception ex){
            System.out.print(ex);

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
