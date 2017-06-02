package com.handacc.amz.analytics.features.account.repo;

import com.handacc.amz.analytics.features.account.dto.CreateAccountRequest;
import com.handacc.amz.analytics.features.account.entity.UserEntity;
import com.handacc.amz.analytics.features.account.service.AccountService;
import org.apache.catalina.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepoTest {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AccountService accountService;

    @Test
    public void simpleSaveTest(){
        UserEntity user = new UserEntity();
        user.setLogin("wlin");
        user.setFirstName("wenhao");
        user.setLastName("lin");
        user.setEmail("wenhao.lin@gmail.com");
        user.setPassword("P@ssword1");
        userRepo.save(user);

        UserEntity saved = userRepo.findOne(user.getId());
        assertNotNull(saved.getId());
        assertEquals(user.getLogin(), saved.getLogin());
    }

    @Test
    public void createAccountTest(){
        CreateAccountRequest accountRequest = new CreateAccountRequest();
         accountRequest.setLogin("yaoyuannie");
         accountRequest.setFirstName("luyuan");
         accountRequest.setLastName("Nie");
         accountRequest.setEmail("nie.luyuan@gmail.com");
         accountRequest.setPassword("123456");
         accountRequest.setPhone("5103005484");
         UserEntity user = accountService.createAccount(accountRequest);
         UserEntity saved = userRepo.findOne(user.getId());
         assertNotNull(saved.getId());
         assertEquals(user.getLogin(), saved.getLogin());
    }
}
