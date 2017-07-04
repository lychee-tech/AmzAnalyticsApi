package com.lychee.amz.analytics.features.account.service;

import com.lychee.amz.analytics.features.account.dto.CreateAccountRequest;
import com.lychee.amz.analytics.features.account.dto.UpdateAccountRequest;
import com.lychee.amz.analytics.features.account.entity.UserEntity;
import com.lychee.amz.analytics.features.account.model.User;
import com.lychee.amz.analytics.features.account.repo.UserRepo;
import org.apache.commons.lang3.StringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AccountServiceTest {
    @Autowired
    UserRepo userRepo;

    @Autowired
    AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
            assertTrue(message.contains("email cannot be empty"));
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
    public void CreateAccountUnqueEmailTest(){
        UserEntity user1 = new UserEntity();
        user1.setLogin("yaoyuannie110");
        user1.setFirstName("dongwei");
        user1.setLastName("sha");
        user1.setEmail("nie.luyuan@gmail.com");
        user1.setPassword("123456");
        user1.setPhone("3dfsf3");
        userRepo.save(user1);

        CreateAccountRequest request = new CreateAccountRequest();
        //test invalid email
        request.setLogin("yaoyuannie");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setEmail("nie.luyuan@gmail.com");
        request.setPassword("P@ssword1");
        request.setPhone("3dfsf3");
        try{
            User serviceResponse2 = accountService.createAccount(request);
        }catch (Exception ex){
            assertTrue(ex instanceof ConstraintViolationException);
            String message = getConstraintViolationsMessage((ConstraintViolationException)ex);
            assertTrue(message.contains("Email has already Registered"));
        }
    }

    @Test
    public void createAccountEmailIsCorrectTest(){
        CreateAccountRequest request = new CreateAccountRequest();
        //test invalid email
        request.setLogin("yaoyuannie");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setEmail("nie.luyuan1@gmail.com");
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
            assertTrue(message.contains("login cannot be empty"));
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
            assertTrue(message.contains("login reach max character limit"));
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
    public void UpdateAccountSuccessTest(){
        UserEntity user = new UserEntity();
        user.setLogin("yaoyuannie");
        user.setFirstName("luyuan");
        user.setLastName("Nie");
        user.setEmail("nie.luyuan@gmail.com");
        user.setPassword("P@ssword1");
        user.setPhone("123456");
        userRepo.save(user);

        UpdateAccountRequest request = new UpdateAccountRequest();
        request.setId(1);
        request.setLogin("yaoyuannie110");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setEmail("nie.luyuan@gmail.com");
        request.setPassword("P@ssword1");
        request.setPhone("123456");

        User updateUser = accountService.updateAccount(request);
        UserEntity saved = userRepo.findById(1);
        assertEquals(updateUser.getLogin(),saved.getLogin());
    }

    @Test
    public void updateAccountNotUpdateEmail(){
        //userId:1
        UserEntity user1 = new UserEntity();
        user1.setLogin("yaoyuannie");
        user1.setFirstName("luyuan");
        user1.setLastName("Nie");
        user1.setEmail("nie.luyuan@gmail.com");
        user1.setPassword("P@ssword1");
        user1.setPhone("3dfsf3");
        userRepo.save(user1);

        //userId:2
        UserEntity user2 = new UserEntity();
        user2.setLogin("yaoyuannie");
        user2.setFirstName("luyuan");
        user2.setLastName("Nie");
        user2.setEmail("test@gmail.com");
        user2.setPassword("P@ssword1");
        user2.setPhone("3dfsf3");
        userRepo.save(user2);

        UpdateAccountRequest request = new UpdateAccountRequest();
        request.setId(2);
        request.setLogin("yaoyuannie110");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setEmail("test@gmail.com");
        request.setPassword("P@ssword1");
        request.setPhone("5103005484");
        try {
            User updateUser2 = accountService.updateAccount(request);
            UserEntity saved = userRepo.findById(2);
            System.out.print("hello");
        }catch (Exception ex){
            assertTrue(ex instanceof ConstraintViolationException);
            String message = getConstraintViolationsMessage((ConstraintViolationException)ex);
            assertTrue(message.contains("Email has already Registered"));

        }
    }

    @Test
    public void UpdateAccountUpdateEmailTest(){
        //userId:1
        UserEntity user1 = new UserEntity();
        user1.setLogin("yaoyuannie");
        user1.setFirstName("luyuan");
        user1.setLastName("Nie");
        user1.setEmail("nie.luyuan@gmail.com");
        user1.setPassword("P@ssword1");
        user1.setPhone("3dfsf3");
        userRepo.save(user1);

        //userId:2
        UserEntity user2 = new UserEntity();
        user2.setLogin("yaoyuannie");
        user2.setFirstName("luyuan");
        user2.setLastName("Nie");
        user2.setEmail("test@gmail.com");
        user2.setPassword("P@ssword1");
        user2.setPhone("3dfsf3");
        userRepo.save(user2);

        UpdateAccountRequest request = new UpdateAccountRequest();
        request.setId(1);
        request.setLogin("yaoyuannie110");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setEmail("test@gmail.com");
        request.setPassword("P@ssword1");
        request.setPhone("3dfsf3");
        try {
            User updateUser = accountService.updateAccount(request);
            UserEntity saved = userRepo.findById(1);
            System.out.print("hello");
        }catch (Exception ex){
            assertTrue(ex instanceof ConstraintViolationException);
            String message = getConstraintViolationsMessage((ConstraintViolationException)ex);
            assertTrue(message.contains("Email has already Registered"));
        }


    }

    @Test
    public void passwordIsEncoded(){
        CreateAccountRequest request = new CreateAccountRequest();
        //test invalid email
        request.setLogin("luyuan");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setEmail("nie.luyuan3@gmail.com");
        request.setPassword("P@ssword1");
        accountService.createAccount(request);
        UserEntity saved = userRepo.findByEmail("nie.luyuan3@gmail.com");
//        assertEquals(passwordEncoder.encode(request.getPassword()),saved.getPassword());
        assertTrue(passwordEncoder.matches(request.getPassword(),saved.getPassword()));
    }

    @Test
    public void phoneValidatorTest(){
        CreateAccountRequest request = new CreateAccountRequest();
        //test invalid email
        request.setLogin("luyuan");
        request.setFirstName("luyuan");
        request.setLastName("Nie");
        request.setEmail("nie.luyuan3@gmail.com");
        request.setPassword("P@ssword1");
        request.setPhone("123dsfsfwe");
        try {
            User creatUser = accountService.createAccount(request);
            UserEntity saved = userRepo.findById(1);
        }catch (Exception ex){
            assertTrue(ex instanceof ConstraintViolationException);
            String message = getConstraintViolationsMessage((ConstraintViolationException)ex);
            assertTrue(message.contains("Your phone can only contains digital"));
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
