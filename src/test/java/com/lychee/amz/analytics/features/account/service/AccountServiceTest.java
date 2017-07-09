package com.lychee.amz.analytics.features.account.service;

import com.lychee.amz.analytics.advice.ConstraintViolationExceptionHelp;
import com.lychee.amz.analytics.advice.MessageAdvice;
import com.lychee.amz.analytics.features.account.dto.CreateAccountRequest;
import com.lychee.amz.analytics.features.account.entity.UserEntity;
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

import javax.validation.ConstraintViolationException;

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
    @Autowired
    private MessageAdvice messageAdvice;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testCreateAccountBlankEmail(){
        try {
            CreateAccountRequest request = new CreateAccountRequest();
            request.setEmail("");
            request.setPassword("P@ssword1");
            accountService.createAccount(request);
        }catch (Exception ex) {
            assertTrue(ex instanceof  ConstraintViolationException);
            String message = getConstraintViolationsMessage((ConstraintViolationException)ex);
            assertTrue(message.contains(messageAdvice.blankEmail));
        }
    }

    @Test
    public void testCreateAccountBadEmail(){
        CreateAccountRequest request = new CreateAccountRequest();
        request.setEmail("123.com");
        request.setPassword("P@ssword1");
        try {
            accountService.createAccount(request);
        }catch (Exception ex){
            assertTrue(ex instanceof ConstraintViolationException);
            String message = getConstraintViolationsMessage((ConstraintViolationException)ex);
            assertTrue(message.contains(messageAdvice.badEmail));
        }
    }

    @Test
    public void CreateAccountUniqueEmailTest(){
        UserEntity user1 = new UserEntity();
        user1.setEmail("dup@gmail.com");
        user1.setEncryptedPassword(passwordEncoder.encode("123456"));
        userRepo.save(user1);

        CreateAccountRequest request = new CreateAccountRequest();

        request.setEmail("dup@gmail.com");
        request.setPassword("P@ssword1");
        try{
            accountService.createAccount(request);
        }catch (Exception ex){
            assertTrue(ex instanceof ConstraintViolationException);
            String message = getConstraintViolationsMessage((ConstraintViolationException)ex);
            assertTrue(message.contains(messageAdvice.duplicateEmail));
        }
    }


    @Test
    public void passwordIsEncoded(){
        CreateAccountRequest request = new CreateAccountRequest();
        request.setEmail("nie.luyuan3@gmail.com");
        request.setPassword("P@ssword1");
        accountService.createAccount(request);
        UserEntity saved = userRepo.findByEmail("nie.luyuan3@gmail.com");

        assertTrue(passwordEncoder.matches(request.getPassword(), saved.getEncryptedPassword()));
    }



    private String getConstraintViolationsMessage(ConstraintViolationException ex){
        List<String> errs = ConstraintViolationExceptionHelp.getViolationMessages(ex);
        return StringUtils.join(errs,";");
    }
}
